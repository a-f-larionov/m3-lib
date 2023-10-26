package m3.lib.dto.rq;

import lombok.*;
import lombok.experimental.SuperBuilder;
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString(callSuper = true)
public class UserIdRqDto {
    private Long userId;
}
