package backend.msclient.infraestructure.persistence.repository;

import backend.msclient.infraestructure.persistence.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaClientRepository extends JpaRepository<ClientEntity, Long> {
}
