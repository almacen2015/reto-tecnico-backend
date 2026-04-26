package backend.msaccount.application.usecase;

import backend.msaccount.domain.model.Account;
import backend.msaccount.domain.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateAccountUseCase {
    private final AccountRepository accountRepository;

    public CreateAccountUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account execute(Account account) {
        return accountRepository.save(account);
    }
}
