package backend.msaccount.infrastructure.repository;

import backend.msaccount.infrastructure.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountJpaRepository extends JpaRepository<AccountEntity, Long> {
}
