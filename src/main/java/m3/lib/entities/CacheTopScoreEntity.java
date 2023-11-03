package m3.lib.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cache_top_score")
public class CacheTopScoreEntity {
    @EmbeddedId
    private CacheTopScoreId id;

    @jakarta.validation.constraints.NotNull
    @Column(name = "place1Uid", nullable = false)
    private Integer place1Uid;

    @jakarta.validation.constraints.NotNull
    @Column(name = "place2Uid", nullable = false)
    private Integer place2Uid;

    @jakarta.validation.constraints.NotNull
    @Column(name = "place3Uid", nullable = false)
    private Integer place3Uid;

    @jakarta.validation.constraints.NotNull
    @Column(name = "pos", nullable = false)
    private Integer pos;

}