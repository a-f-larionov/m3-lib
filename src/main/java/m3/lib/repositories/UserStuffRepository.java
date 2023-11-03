package m3.lib.repositories;

import m3.lib.entities.UsersStuffEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserStuffRepository extends CrudRepository<UsersStuffEntity, Long> {
}
