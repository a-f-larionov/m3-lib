package m3.lib.repositories;

import m3.lib.entities.CacheTopScoreEntity;
import m3.lib.entities.CacheTopScoreId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CacheTopScoreRepository extends JpaRepository<CacheTopScoreEntity, CacheTopScoreId> {
}
