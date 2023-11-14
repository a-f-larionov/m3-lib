package m3.lib.dto.rq;


import lombok.*;
import lombok.experimental.SuperBuilder;
import m3.lib.enums.StatisticEnum;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StatisticRqDto extends UserIdRqDto {
    private StatisticEnum statId;
    private Long time;
    private String paramA;
    private String paramB;
}
