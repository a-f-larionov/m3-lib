package m3.lib.repositories;

import m3.lib.entities.UserPointEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserPointRepository extends JpaRepository<UserPointEntity, Long> {

    @Modifying
    @Query(value = "INSERT INTO users_points(userId, pointId, score) VALUES (?1, ?2, ?3)" +
            "ON CONFLICT(userId, pointId) DO UPDATE SET score = ?3", nativeQuery = true)
    void updateUserPoint(Long userId, Long pointId, Long score);

    @Query(value = "SELECT * FROM users_points WHERE pointId = ?1 ANd userId IN( ?2 ) ORDER BY score DESC LIMIT 3", nativeQuery = true)
    List<UserPointEntity> getTopScore(Long pointId, List<Long> fids);

    @Query(value = "SELECT COUNT(*) as pos " +
            "FROM users_points " +
            " WHERE pointId = ?2 " +
            "  AND (userId IN ( ?3 ) OR userId = ?4 )" +
            "  AND score >= ?1 " +
            " ORDER BY score DESC", nativeQuery = true)
    Long getTopScoreUserPosition(Long score, Long pointId, List<Long> fids, Long userId);

    @Query(value = "SELECT userId, pointId, score FROM " +
            "users_points " +
            "WHERE " +
            " pointId IN( ?1 )" +
            "AND userId IN ( ?2 )", nativeQuery = true)
    List<UserPointEntity> getScores(List<Long> pids, List<Long> fids);
}
