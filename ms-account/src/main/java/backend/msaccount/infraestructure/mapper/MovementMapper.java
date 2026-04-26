package backend.msaccount.infraestructure.mapper;

import backend.msaccount.infraestructure.entity.AccountEntity;
import backend.msaccount.infraestructure.entity.MovementEntity;
import backend.msaccount.domain.model.Movement;

public class MovementMapper {
    public static Movement toDomain(MovementEntity entity) {
        if (entity == null) return null;

        return Movement.builder()
                .id(entity.getId())
                .date(entity.getDate())
                .type(entity.getType())
                .value(entity.getValue())
                .balance(entity.getBalance())
                .accountId(entity.getAccount() != null ? entity.getAccount().getId() : null)
                .build();
    }

    public static MovementEntity toEntity(Movement domain, AccountEntity accountEntity) {
        if (domain == null) return null;

        return MovementEntity.builder()
                .id(domain.getId())
                .date(domain.getDate())
                .type(domain.getType())
                .value(domain.getValue())
                .balance(domain.getBalance())
                .account(accountEntity)
                .build();
    }
}
