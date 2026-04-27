package backend.msaccount.infrastructure.repository;

import backend.msaccount.infrastructure.entity.MovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MovementJpaRepository extends JpaRepository<MovementEntity, Long> {
    List<MovementEntity> findByAccountId(Long accountId);

    List<MovementEntity> findByAccountIdAndDateBetween(Long accountId, LocalDateTime startDate, LocalDateTime endDate);
}
