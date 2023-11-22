package m3.lib.dto.rq;

import lombok.*;
import lombok.experimental.SuperBuilder;
import m3.lib.enums.ClientLogLevels;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LogRqDto extends UserIdRqDto {

    public ClientLogLevels level;
    public String message;
    public Boolean sendToTelegram;
}
