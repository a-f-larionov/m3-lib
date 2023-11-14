package m3.lib.repositories;

import m3.lib.entities.UsersStuffEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserStuffRepository extends CrudRepository<UsersStuffEntity, Long> {

    @Modifying
    @Query(value = "UPDATE users_stuff SET ?1 = ?1 + ?3 WHERE userId = ?2", nativeQuery = true)
    void incrementOne(String filedName, Long userId, Long quantity);
}
