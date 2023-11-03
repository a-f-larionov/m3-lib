package m3.lib.repositories;

import m3.lib.entities.CacheTopScoreEntity;
import m3.lib.entities.CacheTopScoreId;
import org.springframework.data.repository.CrudRepository;

public interface CacheTopScoreRepository extends CrudRepository<CacheTopScoreEntity, CacheTopScoreId> {
}
