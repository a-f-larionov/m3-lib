package m3.lib.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "payments")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @jakarta.validation.constraints.NotNull
    @Column(name = "time", nullable = false)
    private Integer time;

    @jakarta.validation.constraints.NotNull
    @Column(name = "userId", nullable = false)
    private Integer userId;

    @jakarta.validation.constraints.NotNull
    @Column(name = "orderId", nullable = false)
    private Integer orderId;

    @jakarta.validation.constraints.NotNull
    @Column(name = "itemPrice", nullable = false)
    private Integer itemPrice;

}