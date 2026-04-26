package backend.msaccount.application.usecase;

import backend.msaccount.domain.model.Account;
import backend.msaccount.domain.model.Movement;
import backend.msaccount.domain.model.MovementType;
import backend.msaccount.domain.repository.AccountRepository;
import backend.msaccount.domain.repository.MovementRepository;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

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
            throw new IllegalArgumentException("Movement cannot be null");
        }

        if (movement.getValue() <= 0) {
            throw new IllegalArgumentException("Value must be greater than 0");
        }

        Account account = accountRepository.findById(movement.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

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
            if (currentBalance < movement.getValue()) {
                throw new RuntimeException("Insufficient balance");
            }

            newBalance = currentBalance - movement.getValue();

        } else if (Objects.equals(movement.getType(), MovementType.CREDIT.name())) {

            newBalance = currentBalance + movement.getValue();

        } else {
            throw new IllegalArgumentException("Invalid movement type");
        }

        return newBalance;
    }
}
