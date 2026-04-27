package backend.msaccount.application.usecase;

import backend.msaccount.domain.exception.ClientNotFoundException;
import backend.msaccount.domain.model.Account;
import backend.msaccount.domain.repository.AccountRepository;
import backend.msaccount.infrastructure.client.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class CreateAccountUseCase {
    private final AccountRepository accountRepository;
    private final ClientService clientService;

    public CreateAccountUseCase(AccountRepository accountRepository, ClientService clientService) {
        this.accountRepository = accountRepository;
        this.clientService = clientService;
    }

    @Transactional
    public Account execute(Account account) {
        Boolean exists = clientService.existsById(account.getClientId())
                .block();

        if (Boolean.FALSE.equals(exists)) {
            log.error("Client not found with id: {}", account.getClientId());
            throw new ClientNotFoundException("Client not found");
        }

        return accountRepository.save(account);
    }
}
