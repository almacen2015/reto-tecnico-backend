package backend.msaccount.application.usecase;

import backend.msaccount.domain.repository.AccountRepository;
import backend.msaccount.domain.repository.MovementRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class GenerateReportUseCase {
    private final AccountRepository accountRepository;
    private final MovementRepository movementRepository;

    public GenerateReportUseCase(AccountRepository accountRepository, MovementRepository movementRepository) {
        this.accountRepository = accountRepository;
        this.movementRepository = movementRepository;
    }

    public ReportResponse execute(Long clientId, LocalDate start, LocalDate end) {
    }
}
