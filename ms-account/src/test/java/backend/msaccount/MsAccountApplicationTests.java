package backend.msaccount;

import backend.msaccount.domain.model.Account;
import backend.msaccount.domain.model.MovementType;
import backend.msaccount.domain.repository.AccountRepository;
import backend.msaccount.presentation.dto.MovementRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@EntityScan(basePackages = "backend.msaccount.infrastructure.entity")
@EnableJpaRepositories(basePackages = "backend.msaccount.infrastructure")
class MsAccountApplicationTests {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        Account account = Account.builder()
                .number("123")
                .initialBalance(0.00)
                .status(true)
                .clientId(1L)
                .type("AHORROS")
                .build();

        accountRepository.save(account);
    }

    @Test
    void shouldCreateMovementSuccessfully() {
        MovementRequest request = new MovementRequest(
                1L,
                MovementType.CREDIT,
                100.00
        );

        webTestClient.post()
                .uri("/api/v1/movements")
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.balance").isEqualTo(100);
    }

}
