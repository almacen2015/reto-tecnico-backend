package backend.msaccount.presentation.dto;

import lombok.Builder;

@Builder
public record AccountResponse(
        Long id,
        Long clientId,
        String number,
        String type,
        Double balance,
        Boolean status
) {
}
