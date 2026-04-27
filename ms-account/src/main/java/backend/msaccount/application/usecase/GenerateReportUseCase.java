package backend.msaccount.application.usecase;

import backend.msaccount.domain.model.Account;
import backend.msaccount.domain.model.Movement;
import backend.msaccount.domain.repository.AccountRepository;
import backend.msaccount.domain.repository.MovementRepository;
import backend.msaccount.presentation.dto.AccountReport;
import backend.msaccount.presentation.dto.MovementReport;
import backend.msaccount.presentation.dto.ReportResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class GenerateReportUseCase {
    private final AccountRepository accountRepository;
    private final MovementRepository movementRepository;

    public GenerateReportUseCase(AccountRepository accountRepository, MovementRepository movementRepository) {
        this.accountRepository = accountRepository;
        this.movementRepository = movementRepository;
    }

    @Transactional(readOnly = true)
    public ReportResponse execute(Long clientId, LocalDate start, LocalDate end) {
        List<Account> accounts = accountRepository.findByClientId(clientId);

        List<AccountReport> accountReports = accounts.stream()
                .map(account -> {

                    List<Movement> movements =
                            movementRepository.findByAccountIdAndDateBetween(
                                    account.getId(),
                                    start.atStartOfDay(),
                                    end.atTime(23,59,59)
                            );

                    List<MovementReport> movementReports = movements.stream()
                            .map(m -> new MovementReport(
                                    m.getDate(),
                                    m.getType(),
                                    m.getAmount(),
                                    m.getBalance()
                            ))
                            .toList();

                    return new AccountReport(
                            account.getNumber(),
                            account.getType(),
                            account.getInitialBalance(),
                            movementReports
                    );

                }).toList();

        return new ReportResponse(clientId, accountReports);
    }
}
