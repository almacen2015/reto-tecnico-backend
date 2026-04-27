package backend.msclient.application.usecase;

import backend.msclient.domain.exception.ClientNotFoundException;
import backend.msclient.domain.model.Client;
import backend.msclient.domain.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class GetClientByIdUseCase {
    private final ClientRepository clientRepository;

    public GetClientByIdUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional(readOnly = true)
    public Client execute(Long id) {
        log.info("Executing GetClientByIdUseCase with id {}", id);
        return clientRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Client not found with id: {}", id);
                    return new ClientNotFoundException(id);
                });
    }
}
