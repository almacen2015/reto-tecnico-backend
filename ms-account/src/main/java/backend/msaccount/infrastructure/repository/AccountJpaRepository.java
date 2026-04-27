package backend.msaccount.infrastructure.repository;

import backend.msaccount.infrastructure.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountJpaRepository extends JpaRepository<AccountEntity, Long> {
    List<AccountEntity> findByClientId(Long clientId);
}
