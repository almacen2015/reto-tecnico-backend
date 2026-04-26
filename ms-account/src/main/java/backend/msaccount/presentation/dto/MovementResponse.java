package backend.msaccount.presentation.dto;

import backend.msaccount.domain.model.MovementType;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record MovementResponse(
        Long id,
        Long accountId,
        LocalDateTime date,
        MovementType type,
        Double value,
        Double balance
) {
}
