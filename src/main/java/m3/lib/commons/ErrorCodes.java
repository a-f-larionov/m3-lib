package m3.lib.commons;

import lombok.Getter;

@Getter
public enum ErrorCodes {

    VALIDATE_ERROR(1, "Ошибка валидации"),
    AUTH_FAILED(2,"Ошибка авторизации");

    private final Integer code;
    private final String message;

    ErrorCodes(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
