package m3.lib.repositories;

import m3.lib.entities.UsersStuffEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserStuffRepository extends CrudRepository<UsersStuffEntity, Long> {

    @Modifying
    @Query(value = "INSERT INTO users_points(userId, pointId, score) VALUES (?1, ?2, ?3)" +
            "ON DUPLICATE KEY UPDATE score = ?3", nativeQuery = true)
    void updateUsersPoints(Long userId, Long pointId, Long score);
}
