package m3.lib.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class CacheTopScoreId implements Serializable {
    private static final long serialVersionUID = -2438986343318122784L;
    @NotNull
    @Column(name = "userId", nullable = false)
    private Integer userId;

    @NotNull
    @Column(name = "pointId", nullable = false)
    private Integer pointId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CacheTopScoreId entity = (CacheTopScoreId) o;
        return Objects.equals(this.pointId, entity.pointId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pointId, userId);
    }

}