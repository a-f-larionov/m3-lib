package m3.lib.repositories;

import m3.lib.entities.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

    Optional<PaymentEntity> findById(Long id);

    Optional<PaymentEntity> findByOrderId(Long id);
}
