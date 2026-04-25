package backend.msclient.presentation.controller;

import backend.msclient.application.mapper.ClientMapper;
import backend.msclient.application.usecase.CreateClientUseCase;
import backend.msclient.application.usecase.DeleteClientUseCase;
import backend.msclient.application.usecase.GetClientByIdUseCase;
import backend.msclient.domain.model.Client;
import backend.msclient.presentation.dto.ClientRequest;
import backend.msclient.presentation.dto.ClientResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
@RestController
@RequestMapping(name = "/api/v1/customers")
public class ClientController {

    private final GetClientByIdUseCase getClientByIdUseCase;
    private final CreateClientUseCase createClientUseCase;
    private final DeleteClientUseCase deleteClientUseCase;

    public ClientController(GetClientByIdUseCase getClientByIdUseCase, CreateClientUseCase createClientUseCase, DeleteClientUseCase deleteClientUseCase) {
        this.getClientByIdUseCase = getClientByIdUseCase;
        this.createClientUseCase = createClientUseCase;
        this.deleteClientUseCase = deleteClientUseCase;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ClientResponse> deleteClient(@PathVariable("id") Long id) {
        log.info("Request received to delete client with id {}", id);
        return Mono.fromCallable(() -> deleteClientUseCase.execute(id))
                .map(ClientMapper::toResponse)
                .subscribeOn(Schedulers.boundedElastic())
                .doOnSuccess(response ->
                        log.info("Client deleted successfully"))
                .doOnError(throwable ->
                        log.error("Client delete failed with id: {}", id, throwable));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ClientResponse> createClient(@RequestBody @Valid ClientRequest request) {

        log.info("Request received to create client");

        return Mono.fromCallable(() -> ClientMapper.toDomain(request))
                .map(createClientUseCase::execute)
                .map(ClientMapper::toResponse)
                .subscribeOn(Schedulers.boundedElastic())
                .doOnSuccess(response ->
                        log.info("Client created with id: {}", response.id())
                )
                .doOnError(error ->
                        log.error("Error creating client", error)
                );
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Client> getClientById(@PathVariable("id") Long id) {
        log.info("Request received for get client by id {}", id);
        return Mono.fromCallable(() -> getClientByIdUseCase.execute(id))
                .subscribeOn(Schedulers.boundedElastic())
                .doOnSuccess(client ->
                        log.info("Client found with id {}", client.getId()))
                .doOnError(error ->
                        log.error("Error getting client with id {}", id, error));
    }
}
