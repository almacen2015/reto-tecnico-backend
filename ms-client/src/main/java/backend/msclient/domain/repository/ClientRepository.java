package backend.msclient.domain.repository;

import backend.msclient.domain.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    Client save(Client client);

    Optional<Client> findById(Long id);

    List<Client> findAll();

    void deleteById(Long id);
}
