package backend.msclient.infraestructure.repository;

import backend.msclient.infraestructure.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaClientRepository extends JpaRepository<ClientEntity, Long> {
}
