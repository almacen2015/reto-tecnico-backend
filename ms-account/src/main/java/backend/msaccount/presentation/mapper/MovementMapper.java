package backend.msaccount.presentation.mapper;

import backend.msaccount.domain.model.Movement;
import backend.msaccount.presentation.dto.MovementRequest;
import backend.msaccount.presentation.dto.MovementResponse;

import java.time.LocalDateTime;

public class MovementMapper {
    public static Movement toDomain(MovementRequest request) {
        return Movement.builder()
                .accountId(request.accountId())
                .type(request.type().name())
                .value(request.value())
                .date(LocalDateTime.now())
                .build();
    }

    public static MovementResponse toResponse(Movement movement) {
        return MovementResponse.builder()
                .id(movement.getId())
                .accountId(movement.getAccountId())
                .date(movement.getDate())
                .type(movement.getType())
                .value(movement.getValue())
                .balance(movement.getBalance())
                .build();
    }
}
