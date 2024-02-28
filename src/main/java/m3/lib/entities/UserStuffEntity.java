package m3.lib.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import m3.lib.enums.ObjectEnum;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "users_stuff")
public class UserStuffEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "goldQty", nullable = false)
    private Long goldQty;

    @NotNull
    @Column(name = "hummerQty", nullable = false)
    private Long hummerQty;

    @NotNull
    @Column(name = "shuffleQty", nullable = false)
    private Long shuffleQty;

    @NotNull
    @Column(name = "lightningQty", nullable = false)
    private Long lightningQty;

    public void setGoldQty(Long qty) {
        if (qty < 0) {
            throw new RuntimeException("Gold stuff can't be less than zero");
        }
        this.goldQty = qty;
    }

    public void setHummerQty(Long qty) {
        if (qty < 0) {
            throw new RuntimeException("Hummer stuff can't be less than zero");
        }
        this.hummerQty = qty;
    }

    public void setLightningQty(Long qty) {
        if (qty < 0) {
            throw new RuntimeException("Lightning stuff can't be less than zero");
        }
        this.lightningQty = qty;
    }

    public void setShuffleQty(Long qty) {
        if (qty < 0) {
            throw new RuntimeException("Shuffle stuff can't be less than zero");
        }
        this.shuffleQty = qty;
    }

    public Long getQuantityByObject(ObjectEnum objectId) {
        switch (objectId) {
            case STUFF_HUMMER -> {
                return getHummerQty();
            }
            case STUFF_LIGHTNING -> {
                return getLightningQty();
            }
            case STUFF_SHUFFLE -> {
                return getShuffleQty();
            }
            case STUFF_GOLD -> {
                return getGoldQty();
            }
        }
        throw new RuntimeException("Object Id not found");
    }

}