package m3.lib.dto.rq;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import m3.lib.enums.ClientLogLevels;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class LogRqDto {

    private Long userId;
    @NotNull
    public ClientLogLevels level;
    @NotEmpty
    public String message;
    @NotNull
    public Boolean sendToTelegram;
}

