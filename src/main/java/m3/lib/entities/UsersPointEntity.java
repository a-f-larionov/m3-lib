package m3.lib.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users_points")
public class UsersPointEntity {
    @EmbeddedId
    private UsersPointId id;

    @NotNull
    @Column(name = "score", nullable = false)
    private Integer score;

}