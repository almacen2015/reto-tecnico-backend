package backend.msaccount.infraestructure.repository;

import backend.msaccount.domain.entity.MovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovementJpaRepository extends JpaRepository<MovementEntity, Long> {
    List<MovementEntity> findByAccountId(Long accountId);
}
