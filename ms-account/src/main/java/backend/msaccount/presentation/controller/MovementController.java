package backend.msaccount.presentation.controller;

import backend.msaccount.application.usecase.RegisterMovementUseCase;
import backend.msaccount.domain.model.Movement;
import backend.msaccount.presentation.dto.MovementRequest;
import backend.msaccount.presentation.dto.MovementResponse;
import backend.msaccount.presentation.mapper.MovementMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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

    @Operation(summary = "Create a movement", description = "Create a movement")
    @ApiResponse(responseCode = "201", description = "Movement created")
    @ApiResponse(responseCode = "400", description = "Request invalid")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
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
