package m3.lib.repositories;

import m3.lib.entities.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Override
    List<UserEntity> findAll();

    List<UserEntity> findAllByIdIn(List<Long> ids);

    @Modifying
    @Query(value = "UPDATE users SET logout_tm = ?2 WHERE id = ?1", nativeQuery = true)
    int updateLastLogout(@Param("id") Long id, @Param("logout_tm") Long time);

    @Modifying
    @Query(value = "UPDATE users SET login_tm = ?2 WHERE id = ?1", nativeQuery = true)
    int updateLogin(@Param("id") Long id, @Param("login_tm") Long newLoginTimeStamp);

    Optional<UserEntity> findBySocNetTypeIdAndSocNetUserId(Long socNetTypeId, Long socNetUserId);

    @Query(value = "SELECT id FROM users WHERE socNetTypeId = ?1 AND socNetUserId IN ?2 ORDER BY nextPointId", nativeQuery = true)
    List<Long> findIdBySocNetTypeIdAndSocNetUserIdIn(@NonNull Long socNetTypeId, @NonNull List<Long> socNetUserId);

    @Query(value =
            "SELECT id FROM users " +
                    "WHERE nextPointId >= ?1 " +
                    "AND nextPointId <= ?2 " +
                    "AND socNetUserId IN (?3)",
            nativeQuery = true)
    List<Long> gotMapFriends(Long firstPointId, Long lastPointId, List<Long> fids);

    List<UserEntity> findAllByIdInOrderByNextPointIdDesc(List<Long> ids, Pageable pageable);

    @Modifying
    @Query(value = "UPDATE users SET fullRecoveryTime = ?2 WHERE id = ?1", nativeQuery = true)
    void updateHealth(Long id, Long fullRecoveryTime);

    @Modifying
    @Query(value = "UPDATE users SET nextPointId = ?2 WHERE id = ?1 AND nextPointId < ?2 ", nativeQuery = true)
    void nextPointUp(Long userId, Long pointId);
}