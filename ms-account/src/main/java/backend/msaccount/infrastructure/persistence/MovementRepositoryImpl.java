package backend.msaccount.infrastructure.persistence;

import backend.msaccount.domain.model.Movement;
import backend.msaccount.domain.repository.MovementRepository;
import backend.msaccount.infrastructure.entity.AccountEntity;
import backend.msaccount.infrastructure.entity.MovementEntity;
import backend.msaccount.infrastructure.mapper.MovementMapper;
import backend.msaccount.infrastructure.repository.AccountJpaRepository;
import backend.msaccount.infrastructure.repository.MovementJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MovementRepositoryImpl implements MovementRepository {
    private final MovementJpaRepository movementJpaRepository;
    private final AccountJpaRepository accountJpaRepository;

    public MovementRepositoryImpl(MovementJpaRepository movementJpaRepository, AccountJpaRepository accountJpaRepository) {
        this.movementJpaRepository = movementJpaRepository;
        this.accountJpaRepository = accountJpaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movement> findByAccountIdAndDateBetween(Long accountId, LocalDateTime startDate, LocalDateTime endDate) {
        List<MovementEntity> movementsEntity = movementJpaRepository.findByAccountIdAndDateBetween(accountId, startDate, endDate);

        return movementsEntity.stream()
                .map(MovementMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Movement save(Movement movement) {
        AccountEntity account = accountJpaRepository.findById(movement.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        MovementEntity entity = MovementMapper.toEntity(movement, account);

        return MovementMapper.toDomain(movementJpaRepository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movement> findByAccountId(Long accountId) {
        return movementJpaRepository.findByAccountId(accountId)
                .stream()
                .map(MovementMapper::toDomain)
                .collect(Collectors.toList());
    }
}
