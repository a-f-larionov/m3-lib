package m3.lib.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ClientLogLevels {
    TRACE(1, "Детально"),
    DEBUG(2, "Оповещение"),
    INFO(3, "Оповещение"),
    WARN(4, "Внимание"),
    ERROR(5, "Ошибка");

    private final int level;
    private final String title;
}
