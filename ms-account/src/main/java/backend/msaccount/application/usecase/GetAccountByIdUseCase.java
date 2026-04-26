package backend.msaccount.application.usecase;

import backend.msaccount.domain.model.Account;
import backend.msaccount.domain.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class GetAccountByIdUseCase {
    private final AccountRepository accountRepository;

    public GetAccountByIdUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account execute(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }
}
