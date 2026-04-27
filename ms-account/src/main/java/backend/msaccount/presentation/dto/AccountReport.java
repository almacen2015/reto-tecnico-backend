package backend.msaccount.presentation.dto;

import java.util.List;

public record AccountReport(
        String accountNumber,
        String type,
        Double balance,
        List<MovementReport> movements
) {
}
