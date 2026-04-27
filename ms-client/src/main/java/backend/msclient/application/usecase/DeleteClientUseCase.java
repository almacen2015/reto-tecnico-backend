package backend.msclient.application.usecase;

import backend.msclient.domain.exception.ClientNotFoundException;
import backend.msclient.domain.model.Client;
import backend.msclient.domain.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class DeleteClientUseCase {
    private final ClientRepository clientRepository;

    public DeleteClientUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public Client execute(Long id) {
        log.info("Deleting client with identification: {}", id);

        Client client = clientRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Cliente not found with id: {}", id);
                    return new ClientNotFoundException(id);
                });

        client.setStatus(false);

        Client updated = clientRepository.save(client);

        log.info("Cliente soft deleted with id: {}", id);

        return updated;
    }
}
