package backend.msaccount.domain.repository;

import backend.msaccount.domain.model.Movement;

import java.time.LocalDateTime;
import java.util.List;

public interface MovementRepository {
    Movement save(Movement movement);

    List<Movement> findByAccountId(Long accountId);

    List<Movement> findByAccountIdAndDateBetween(Long accountId, LocalDateTime startDate, LocalDateTime endDate);
}
