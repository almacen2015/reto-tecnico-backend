package backend.msaccount.application.usecase;

import backend.msaccount.domain.model.Account;
import backend.msaccount.domain.repository.AccountRepository;
import backend.msaccount.infraestructure.client.ClientService;
import org.springframework.stereotype.Service;

@Service
public class CreateAccountUseCase {
    private final AccountRepository accountRepository;
    private final ClientService clientService;

    public CreateAccountUseCase(AccountRepository accountRepository, ClientService clientService) {
        this.accountRepository = accountRepository;
        this.clientService = clientService;
    }

    public Account execute(Account account) {
        Boolean exists = clientService.existsById(account.getClientId())
                .block();

        if (Boolean.FALSE.equals(exists)) {
            throw new RuntimeException("Client not found");
        }

        return accountRepository.save(account);
    }
}
