package backend.msaccount.presentation.mapper;

import backend.msaccount.domain.model.Account;
import backend.msaccount.presentation.dto.AccountRequest;
import backend.msaccount.presentation.dto.AccountResponse;

public class AccountMapper {
    public static Account toDomain(AccountRequest request) {
        return Account.builder()
                .clientId(request.clientId())
                .number(request.number())
                .type(request.type())
                .initialBalance(request.initialBalance())
                .status(request.status())
                .build();
    }

    public static AccountResponse toResponse(Account account) {
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
