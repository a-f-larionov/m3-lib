package m3.lib.dto.rs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import m3.lib.commons.ErrorCodes;

@AllArgsConstructor
@Getter
public class ErrorRsDto {

    private final ErrorCodes errorCodes;
    private final String message;
    private final Long userId;
    private final String packetFrom;
}
