package m3.lib.repositories;

import m3.lib.entities.PaymentEntity;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<PaymentEntity, Long> {
}
