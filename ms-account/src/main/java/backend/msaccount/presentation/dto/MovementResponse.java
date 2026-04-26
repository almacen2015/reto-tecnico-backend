package backend.msaccount.presentation.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record MovementResponse(
        Long id,
        Long accountId,
        LocalDateTime date,
        String type,
        Double value,
        Double balance
) {
}
