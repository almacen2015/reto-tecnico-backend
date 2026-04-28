package backend.msaccount.presentation.exception;

import backend.msaccount.domain.exception.AccountNotFoundException;
import backend.msaccount.domain.exception.ClientInactiveException;
import backend.msaccount.domain.exception.InsufficientBalanceException;
import backend.msaccount.domain.exception.InvalidMovementException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ClientInactiveException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<ErrorResponse> handleInvalidEnum(ClientInactiveException ex, ServerWebExchange request) {
        return Mono.just(
                ErrorResponse.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message(ex.getMessage())
                        .path(request.getRequest().getPath().toString())
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(ServerWebInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<ErrorResponse> handleInvalidEnum(ServerWebInputException ex, ServerWebExchange request) {
        log.error(ex.getMessage(), ex);

        String message = "Invalid request";

        return Mono.just(
                ErrorResponse.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message(message)
                        .path(request.getRequest().getPath().toString())
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Mono<ErrorResponse> handleAccountNotFound(AccountNotFoundException ex, ServerWebExchange request) {

        log.error("Account not found: {}", ex.getMessage());

        return Mono.just(ErrorResponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .path(request.getRequest().getPath().toString())
                .timestamp(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<ErrorResponse> handleInsufficientBalance(InsufficientBalanceException ex, ServerWebExchange request) {

        log.error("Insufficient balance: {}", ex.getMessage());

        return Mono.just(ErrorResponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequest().getPath().toString())
                .timestamp(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler(InvalidMovementException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<ErrorResponse> handleInvalidMovement(InvalidMovementException ex, ServerWebExchange request) {

        log.error("Invalid movement: {}", ex.getMessage());

        return Mono.just(ErrorResponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequest().getPath().toString())
                .timestamp(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<ErrorResponse> handleValidation(MethodArgumentNotValidException ex, ServerWebExchange request) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse("Validation error");

        log.error("Validation error: {}", message);

        return Mono.just(ErrorResponse.builder()
                .message(message)
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequest().getPath().toString())
                .timestamp(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Mono<ErrorResponse> handleGeneric(Exception ex, ServerWebExchange request) {

        log.error("Unexpected error", ex);

        return Mono.just(ErrorResponse.builder()
                .message("Internal server error")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .path(request.getRequest().getPath().toString())
                .timestamp(LocalDateTime.now())
                .build());
    }
}
