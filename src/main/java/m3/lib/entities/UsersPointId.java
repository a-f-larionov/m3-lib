package m3.lib.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class UsersPointId implements Serializable {
    private static final long serialVersionUID = -675811616460046639L;
    @jakarta.validation.constraints.NotNull
    @Column(name = "userId", nullable = false)
    private Long userId;

    @jakarta.validation.constraints.NotNull
    @Column(name = "pointId", nullable = false)
    private Long pointId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UsersPointId entity = (UsersPointId) o;
        return Objects.equals(this.pointId, entity.pointId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pointId, userId);
    }

}