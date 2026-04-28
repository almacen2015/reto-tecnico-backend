package backend.msaccount.infrastructure.client.dto;

import lombok.Builder;

@Builder
public record ClientResponse(
        Long id,
        Boolean status
) {
}
