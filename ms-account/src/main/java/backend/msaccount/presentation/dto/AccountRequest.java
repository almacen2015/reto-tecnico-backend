package backend.msaccount.presentation.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record AccountRequest(
        @NotNull(message = "ClientId is required")
        Long clientId,

        @NotNull(message = "Account number is required")
        String number,

        @NotNull(message = "Account type is required")
        String type,

        @NotNull(message = "Initial balance is required")
        Double initialBalance,

        @NotNull(message = "Status is required")
        Boolean status
) {
}
