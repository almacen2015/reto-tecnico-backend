package backend.msaccount.infrastructure.client;

import backend.msaccount.infrastructure.client.dto.ClientResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ClientService {

    private final WebClient webClient;

    public ClientService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<Boolean> isClientActive(Long id) {
        return webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(ClientResponse.class)
                .map(ClientResponse::status)
                .defaultIfEmpty(false)
                .onErrorResume(ex -> Mono.just(false));

    }
}
