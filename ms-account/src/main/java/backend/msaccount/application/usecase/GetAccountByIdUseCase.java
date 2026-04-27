package backend.msaccount.application.usecase;

import backend.msaccount.domain.exception.AccountNotFoundException;
import backend.msaccount.domain.model.Account;
import backend.msaccount.domain.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class GetAccountByIdUseCase {
    private final AccountRepository accountRepository;

    public GetAccountByIdUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional(readOnly = true)
    public Account execute(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Account not found with id: {}", id);
                    return new AccountNotFoundException("Account not found");
                });
    }
}
