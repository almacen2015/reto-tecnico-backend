package backend.msaccount.presentation.dto;

import java.util.List;

public record ReportResponse(
        Long clientId,
        List<AccountReport> accounts
) {
}
