package backend.msaccount.presentation.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AccountRequest(
        @NotNull(message = "ClientId is required")
        Long clientId,

        @NotNull(message = "Account number is required")
        String number,

        @NotNull(message = "Account type is required")
        String type,

        @NotNull(message = "Initial balance is required")
        @Positive(message = "Initial balance must be greater than zero")
        Double initialBalance,

        @NotNull(message = "Status is required")
        Boolean status
) {
}
