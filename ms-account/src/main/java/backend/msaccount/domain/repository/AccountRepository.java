package backend.msaccount.domain.repository;

import backend.msaccount.domain.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    Account save(Account account);

    Optional<Account> findById(Long id);

    List<Account> findAll();

    void updateStatus(Long id, Boolean status);

    List<Account> findByClientId(Long clientId);
}
