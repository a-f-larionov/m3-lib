package m3.lib.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users_stuff")
public class UserStuffEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`userId`", nullable = false)
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

}