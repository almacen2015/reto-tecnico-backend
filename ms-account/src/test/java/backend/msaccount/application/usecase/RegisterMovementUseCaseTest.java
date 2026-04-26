package backend.msaccount.application.usecase;

import backend.msaccount.domain.exception.AccountNotFoundException;
import backend.msaccount.domain.exception.InsufficientBalanceException;
import backend.msaccount.domain.model.Account;
import backend.msaccount.domain.model.Movement;
import backend.msaccount.domain.model.MovementType;
import backend.msaccount.domain.repository.AccountRepository;
import backend.msaccount.domain.repository.MovementRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegisterMovementUseCaseTest {
    @Mock
    private AccountRepository accountRepository;

    @Mock
    private MovementRepository movementRepository;

    @InjectMocks
    private RegisterMovementUseCase useCase;

    @Test
    void shouldThrowException_whenAccountNotFound() {

        Movement movement = Movement.builder()
                .accountId(1L)
                .type(MovementType.CREDIT.name())
                .value(50.00)
                .date(LocalDateTime.now())
                .build();

        when(accountRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class,
                () -> useCase.execute(movement));
    }

    @Test
    void shouldThrowException_whenInsufficientBalance() {

        Account account = Account.builder()
                .id(1L)
                .initialBalance(50.00)
                .build();

        Movement movement = Movement.builder()
                .accountId(1L)
                .type(MovementType.DEBIT.name())
                .value(100.00)
                .date(LocalDateTime.now())
                .build();

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        assertThrows(InsufficientBalanceException.class,
                () -> useCase.execute(movement));
    }

    @Test
    void shouldAddBlance_whenMovementIsCredit() {

        Account account = Account.builder()
                .id(1L)
                .initialBalance(100.00)
                .build();

        Movement movement = Movement.builder()
                .accountId(1L)
                .type(MovementType.CREDIT.name())
                .value(50.00)
                .date(LocalDateTime.now())
                .build();

        when(accountRepository.findById(1L))
                .thenReturn(Optional.of(account));
        when(movementRepository.save(any()))
                .thenAnswer(inv -> inv.getArgument(0)); // "te devuelvo lo mismo que me diste"

        Movement result = useCase.execute(movement);

        assertEquals(150.00, result.getBalance());
        verify(accountRepository).save(account);
        verify(movementRepository).save(any());
    }

    @Test
    void shouldSubtractBalance_whenMovementIsDebit() {

        Account account = Account.builder()
                .id(1L)
                .initialBalance(100.00)
                .build();

        Movement movement = Movement.builder()
                .accountId(1L)
                .type(MovementType.DEBIT.name())
                .value(40.00)
                .date(LocalDateTime.now())
                .build();

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(movementRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Movement result = useCase.execute(movement);

        assertEquals(60.00, result.getBalance());
    }
}