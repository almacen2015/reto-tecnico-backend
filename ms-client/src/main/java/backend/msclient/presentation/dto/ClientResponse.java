package backend.msclient.presentation.dto;

import lombok.Builder;

@Builder
public record ClientResponse(
        Long id,
        String name,
        String gender,
        String identification,
        String address,
        String phone,
        String password,
        Boolean status
) {
}
