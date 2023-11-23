package m3.lib.repositories;

import m3.lib.entities.UserAgentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAgentRepository extends JpaRepository<UserAgentEntity, Long> {
}
