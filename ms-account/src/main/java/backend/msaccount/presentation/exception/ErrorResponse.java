package backend.msaccount.presentation.exception;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorResponse(
        String message,
        int status,
        String path,
        LocalDateTime timestamp
) {
}
