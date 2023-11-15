package m3.lib.repositories;

import m3.lib.entities.UserPointEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserPointRepository extends CrudRepository<UserPointEntity, Long> {

    @Modifying
    @Query(value = "INSERT INTO users_points(userId, pointId, score) VALUES (?1, ?2, ?3)" +
            "ON DUPLICATE KEY UPDATE score = ?3", nativeQuery = true)
    void updateUserPoint(Long userId, Long pointId, Long score);

}
