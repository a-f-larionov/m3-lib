package m3.lib.repositories;

import m3.lib.entities.UserStuffEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserStuffRepository extends CrudRepository<UserStuffEntity, Long> {
    @Query(value = "SELECT * FROM users_stuff WHERE `userId` = ?1", nativeQuery = true)
    UserStuffEntity getByUserId(Long userId);

    @Modifying
    @Query(value = "UPDATE users_stuff SET `goldQty` = `goldQty` + ?2 WHERE `userId` = ?1", nativeQuery = true)
    void incrementGoldQty(Long userId, Long quantity);

    @Modifying
    @Query(value = "UPDATE users_stuff SET hummerQty = hummerQty + ?2 WHERE userId = ?1", nativeQuery = true)
    void incrementHummerQty(Long userId, Long quantity);

    @Modifying
    @Query(value = "UPDATE users_stuff SET shuffleQty = shuffleQty + ?2 WHERE userId = ?1", nativeQuery = true)
    void incrementShuffleQty(Long userId, Long quantity);

    @Modifying
    @Query(value = "UPDATE users_stuff SET lightningQty = lightningQty + ?2 WHERE userId = ?1", nativeQuery = true)
    void incrementLightningQty(Long userId, Long quantity);

    @Modifying
    @Query(value = "INSERT INTO users_stuff " +
            "(userId, goldQty, hummerQty, lightningQty, shuffleQty) " +
            "VALUES (?1, 500, 2, 1, 1)", nativeQuery = true)
    void creatUserStuff(Long userId);
}
