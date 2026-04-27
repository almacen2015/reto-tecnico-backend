package backend.msaccount.infrastructure.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ClientService {

    private final WebClient webClient;

    public ClientService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<Boolean> existsById(Long id) {
        return webClient.get()
                .uri("/clients/{id}", id)
                .retrieve()
                .bodyToMono(Object.class)
                .map(response -> true)
                .onErrorResume(ex -> Mono.just(false));

    }
}
