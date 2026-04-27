package backend.msclient.presentation.dto;

import backend.msclient.domain.model.Gender;
import lombok.Builder;

@Builder
public record ClientResponse(
        Long id,
        String name,
        Gender gender,
        String identification,
        String address,
        String phone,
        String password,
        Boolean status
) {
}
