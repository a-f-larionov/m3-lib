package m3.lib.repositories;

import m3.lib.entities.UserAgentEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserAgentRepository extends CrudRepository<UserAgentEntity, Long> {

}
