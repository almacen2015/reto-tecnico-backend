package backend.msclient.presentation.exception;

import lombok.Builder;

@Builder
public record ErrorResponse(
        String message,
        int status
) {
}
