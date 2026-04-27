package backend.msaccount.presentation.dto;

import backend.msaccount.domain.model.MovementType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record MovementRequest(
        @NotNull(message = "AccountId is required")
        Long accountId,

        @NotNull(message = "Type is required")
        MovementType type,

        @NotNull(message = "Value is required")
        @Positive(message = "Value must be greater than zero")
        Double amount
) {
}
