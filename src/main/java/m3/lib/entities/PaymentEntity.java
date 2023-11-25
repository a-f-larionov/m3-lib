package m3.lib.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "payments")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @jakarta.validation.constraints.NotNull
    @Column(name = "time", nullable = false)
    private Long time;

    @jakarta.validation.constraints.NotNull
    @Column(name = "userId", nullable = false)
    private Long userId;

    @jakarta.validation.constraints.NotNull
    @Column(name = "orderId", nullable = false)
    private Long orderId;

    @jakarta.validation.constraints.NotNull
    @Column(name = "itemPrice", nullable = false)
    private Long itemPrice;

}