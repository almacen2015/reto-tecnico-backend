package backend.msaccount.presentation;

import backend.msaccount.domain.model.Account;
import backend.msaccount.presentation.dto.AccountRequest;
import backend.msaccount.presentation.dto.AccountResponse;

public class AccountMapper {
    public Account toDomain(AccountRequest request) {
        return Account.builder()
                .clientId(request.clientId())
                .number(request.number())
                .type(request.type())
                .initialBalance(request.initialBalance())
                .status(request.status())
                .build();
    }

    public AccountResponse toResponse(Account account) {
        return AccountResponse.builder()
                .id(account.getId())
                .clientId(account.getClientId())
                .number(account.getNumber())
                .type(account.getType())
                .balance(account.getInitialBalance())
                .status(account.getStatus())
                .build();
    }
}
