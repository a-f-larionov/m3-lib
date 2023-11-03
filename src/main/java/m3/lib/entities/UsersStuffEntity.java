package m3.lib.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users_stuff")
public class UsersStuffEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "hummerQty", nullable = false)
    private Integer hummerQty;

    @NotNull
    @Column(name = "shuffleQty", nullable = false)
    private Integer shuffleQty;

    @NotNull
    @Column(name = "goldQty", nullable = false)
    private Integer goldQty;

    @NotNull
    @Column(name = "lightningQty", nullable = false)
    private Integer lightningQty;

}