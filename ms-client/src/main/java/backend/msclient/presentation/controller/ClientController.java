package backend.msclient.presentation.controller;

import backend.msclient.application.usecase.GetClientByIdUseCase;
import backend.msclient.domain.model.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
@RestController
@RequestMapping(name = "/api/clients")
public class ClientController {

    private final GetClientByIdUseCase getClientByIdUseCase;

    public ClientController(GetClientByIdUseCase getClientByIdUseCase) {
        this.getClientByIdUseCase = getClientByIdUseCase;
    }


    @GetMapping("/{id}")
    public Mono<Client> getClienteById(@PathVariable Long id) {
        log.info("Request received for get client by id {}", id);
        return Mono.fromCallable(() -> getClientByIdUseCase.execute(id))
                .subscribeOn(Schedulers.boundedElastic())
                .doOnSuccess(client ->
                        log.info("Client found with id {}", client.getId()))
                .doOnError(error ->
                        log.error("Error getting client with id {}", id, error));
    }
}
