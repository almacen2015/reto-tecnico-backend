package backend.msclient.application.usecase;

import backend.msclient.domain.model.Client;
import backend.msclient.domain.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class CreateClientUseCase {
    private final ClientRepository clientRepository;

    public CreateClientUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public Client execute(Client client) {
        log.info("Creating client with identification: {}", client.getIdentification());
        return clientRepository.save(client);
    }
}
