package backend.msaccount.presentation.dto;

import java.time.LocalDateTime;

public record MovementReport(
        LocalDateTime date,
        String type,
        Double amount,
        Double balance
) {
}
