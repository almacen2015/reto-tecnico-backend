package backend.msclient.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClientRequest(
        @NotBlank
        String name,

        @NotBlank
        String gender,

        @NotBlank
        String identification,

        @NotBlank
        String address,

        @NotBlank
        String phone,

        @NotBlank
        String password,

        @NotNull
        Boolean status
        ) {
}
