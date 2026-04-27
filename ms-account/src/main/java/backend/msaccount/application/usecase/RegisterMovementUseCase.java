package backend.msaccount.application.usecase;

import backend.msaccount.domain.exception.AccountNotFoundException;
import backend.msaccount.domain.exception.InsufficientBalanceException;
import backend.msaccount.domain.exception.InvalidMovementException;
import backend.msaccount.domain.model.Account;
import backend.msaccount.domain.model.Movement;
import backend.msaccount.domain.model.MovementType;
import backend.msaccount.domain.repository.AccountRepository;
import backend.msaccount.domain.repository.MovementRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@Slf4j
public class RegisterMovementUseCase {
    private final AccountRepository accountRepository;
    private final MovementRepository movementRepository;

    public RegisterMovementUseCase(AccountRepository accountRepository, MovementRepository movementRepository) {
        this.accountRepository = accountRepository;
        this.movementRepository = movementRepository;
    }

    @Transactional
    public Movement execute(Movement movement) {
        if (movement == null) {
            throw new InvalidMovementException("Movement cannot be null");
        }

        if (movement.getAmount() <= 0) {
            throw new InvalidMovementException("Value must be greater than 0");
        }

        Account account = accountRepository.findById(movement.getAccountId())
                .orElseThrow(() -> {
                    log.error("Account not found with id: {}", movement.getAccountId());
                    return new AccountNotFoundException("Account not found");
                });

        double currentBalance = account.getInitialBalance();

        double newBalance = processMovement(movement, currentBalance);

        account.setInitialBalance(newBalance);
        accountRepository.save(account);

        movement.setDate(LocalDateTime.now());
        movement.setBalance(newBalance);

        return movementRepository.save(movement);
    }

    private double processMovement(Movement movement, double currentBalance) {
        double newBalance;

        if (Objects.equals(movement.getType(), MovementType.DEBIT.name())) {
            if (currentBalance < movement.getAmount()) {
                throw new InsufficientBalanceException("Insufficient balance");
            }

            newBalance = currentBalance - movement.getAmount();

        } else if (Objects.equals(movement.getType(), MovementType.CREDIT.name())) {

            newBalance = currentBalance + movement.getAmount();

        } else {
            throw new InvalidMovementException("Invalid movement type");
        }

        return newBalance;
    }
}
