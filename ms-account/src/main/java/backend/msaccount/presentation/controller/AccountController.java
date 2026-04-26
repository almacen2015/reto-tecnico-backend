package backend.msaccount.presentation.controller;

import backend.msaccount.application.usecase.CreateAccountUseCase;
import backend.msaccount.application.usecase.GetAccountByIdUseCase;
import backend.msaccount.domain.model.Account;
import backend.msaccount.presentation.mapper.AccountMapper;
import backend.msaccount.presentation.dto.AccountRequest;
import backend.msaccount.presentation.dto.AccountResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping("/api/v1/accounts")
@Slf4j
public class AccountController {

    private final CreateAccountUseCase createAccountUseCase;
    private final GetAccountByIdUseCase getAccountByIdUseCase;

    public AccountController(CreateAccountUseCase createAccountUseCase, GetAccountByIdUseCase getAccountByIdUseCase) {
        this.createAccountUseCase = createAccountUseCase;
        this.getAccountByIdUseCase = getAccountByIdUseCase;
    }

    @PostMapping
    public Mono<AccountResponse> create(@Valid @RequestBody AccountRequest request) {

        log.info("Creating account for clientId={}", request.clientId());

        return Mono.fromCallable(() -> {
                    Account account = AccountMapper.toDomain(request);
                    return createAccountUseCase.execute(account);
                })
                .subscribeOn(Schedulers.boundedElastic())
                .map(AccountMapper::toResponse)
                .doOnSuccess(res ->
                        log.info("Account created successfully id={}", res.id()))
                .doOnError(err ->
                        log.error("Error creating account", err));
    }

    @GetMapping("/{id}")
    public Mono<AccountResponse> getById(@PathVariable("id") Long id) {

        log.info("Fetching account id={}", id);

        return Mono.fromCallable(() -> getAccountByIdUseCase.execute(id))
                .subscribeOn(Schedulers.boundedElastic())
                .map(AccountMapper::toResponse)
                .doOnSuccess(res ->
                        log.info("Account found id: {}", id))
                .doOnError(err ->
                        log.error("Error fetching account id: {}", id, err));
    }
}
