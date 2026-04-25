package backend.msclient.infraestructure.persistence.repository;

import backend.msclient.domain.model.Client;
import backend.msclient.domain.repository.ClientRepository;
import backend.msclient.infraestructure.persistence.entity.ClientEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepositoryImpl implements ClientRepository {
    private final JpaClientRepository jpaClientRepository;

    public ClientRepositoryImpl(JpaClientRepository jpaClientRepository) {
        this.jpaClientRepository = jpaClientRepository;
    }

    @Override
    public Client save(Client client) {
        ClientEntity clientEntity = toEntity(client);
        return toDomain(jpaClientRepository.save(clientEntity));
    }

    @Override
    public Optional<Client> findById(Long id) {
        return jpaClientRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public List<Client> findAll() {
        return jpaClientRepository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaClientRepository.deleteById(id);
    }

    private Client toDomain(ClientEntity entity) {
        return Client.builder()
                .id(entity.getId())
                .name(entity.getName())
                .gender(entity.getGender())
                .identification(entity.getIdentification())
                .address(entity.getAddress())
                .phone(entity.getPhone())
                .password(entity.getPassword())
                .status(entity.getStatus())
                .build();
    }

    private ClientEntity toEntity(Client client) {
        return ClientEntity.builder()
                .id(client.getId())
                .name(client.getName())
                .gender(client.getGender())
                .identification(client.getIdentification())
                .address(client.getAddress())
                .phone(client.getPhone())
                .password(client.getPassword())
                .status(client.getStatus())
                .build();
    }
}
