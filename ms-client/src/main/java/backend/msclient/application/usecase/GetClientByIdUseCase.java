package backend.msclient.application.usecase;

import backend.msclient.domain.model.Client;
import backend.msclient.domain.repository.ClientRepository;

public class GetClientByIdUseCase {
    private final ClientRepository clientRepository;

    public GetClientByIdUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client execute(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente not found"));
    }
}
