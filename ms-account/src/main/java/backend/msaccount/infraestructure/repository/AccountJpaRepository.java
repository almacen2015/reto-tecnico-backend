package backend.msaccount.infraestructure.repository;

import backend.msaccount.infraestructure.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountJpaRepository extends JpaRepository<AccountEntity, Long> {
}
