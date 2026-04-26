package backend.msaccount.presentation.controller;

import backend.msaccount.application.usecase.RegisterMovementUseCase;
import backend.msaccount.domain.model.Movement;
import backend.msaccount.presentation.dto.MovementRequest;
import backend.msaccount.presentation.dto.MovementResponse;
import backend.msaccount.presentation.mapper.MovementMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping("/api/v1/movements")
@Slf4j
public class MovementController {

    private final RegisterMovementUseCase registerMovementUseCase;

    public MovementController(RegisterMovementUseCase registerMovementUseCase) {
        this.registerMovementUseCase = registerMovementUseCase;
    }


    @PostMapping
    public Mono<MovementResponse> create(@Valid @RequestBody MovementRequest request) {

        log.info("Creating movement for accountId={}", request.accountId());

        return Mono.fromCallable(() -> {
                    Movement movement = MovementMapper.toDomain(request);
                    return registerMovementUseCase.execute(movement);
                })
                .subscribeOn(Schedulers.boundedElastic())
                .map(MovementMapper::toResponse)
                .doOnSuccess(res ->
                        log.info("Movement created id={}", res.id()))
                .doOnError(err ->
                        log.error("Error creating movement", err));
    }
}
