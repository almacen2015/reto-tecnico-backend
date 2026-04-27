package backend.msclient.presentation.dto;

import backend.msclient.domain.model.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ClientRequest(
        @NotBlank(message = "The name is required")
        String name,

        @NotNull(message = "The gender is required")
        Gender gender,

        @NotBlank(message = "The identification is required")
        String identification,

        @NotBlank
        String address,

        @NotBlank
        String phone,

        @NotBlank(message = "The password is required")
        String password,

        @NotNull(message = "The status is required")
        Boolean status
        ) {
}
