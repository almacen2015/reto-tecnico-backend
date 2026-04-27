package backend.msaccount.infrastructure.mapper;

import backend.msaccount.infrastructure.entity.AccountEntity;
import backend.msaccount.domain.model.Account;

public class AccountMapper {
    public static Account toDomain(AccountEntity entity) {
        if (entity == null) return null;

        return Account.builder()
                .id(entity.getId())
                .number(entity.getNumber())
                .type(entity.getType())
                .initialBalance(entity.getInitialBalance())
                .status(entity.getStatus())
                .clientId(entity.getClientId())
                .build();
    }

    public static AccountEntity toEntity(Account domain) {
        if (domain == null) return null;

        return AccountEntity.builder()
                .id(domain.getId())
                .number(domain.getNumber())
                .type(domain.getType())
                .initialBalance(domain.getInitialBalance())
                .status(domain.getStatus())
                .clientId(domain.getClientId())
                .build();
    }
}
