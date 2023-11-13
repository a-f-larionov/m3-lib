package m3.lib.kafka;

import ch.qos.logback.classic.spi.IThrowableProxy;
import lombok.RequiredArgsConstructor;
import m3.lib.commons.ErrorCodes;
import m3.lib.dto.rq.UserIdRqDto;
import m3.lib.dto.rs.ErrorRsDto;
import m3.lib.helpers.TelegramSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.String.format;

@Component(value = "validationErrorHandler")
@RequiredArgsConstructor
public class KafkaErrorHandler implements KafkaListenerErrorHandler {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    @Value("${alerter.telegram.token}")
    private String teleToken;
    @Value("${alerter.telegram.chatId}")
    private String chatId;

    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException exception) {

        var payload = message.getPayload();
        var userId = getUserIdFromPayLoad(payload);
        var payloadClassName = payload.getClass().getName();

        String errorMsg = createErrorMessage(exception, payload);

        sendToTelegram(errorMsg, payloadClassName, userId);

        exception.printStackTrace(System.out);

        if (userId != null) {
            kafkaTemplate.send("topic-client", createErrorRsDto(errorMsg, payloadClassName, userId));
        }
        return null;
    }

    private static String createErrorMessage(ListenerExecutionFailedException exception, Object payload) {
        String errorMsg;
        errorMsg = extractErrorMessage(exception);
        errorMsg += "\r\n\r\n--- PAYLOAD ---\r\n" + payload.toString().replace(", ", ",\r\n");
        return errorMsg;
    }

    private static ErrorRsDto createErrorRsDto(String errorMsg, String className, Long userId) {
        return new ErrorRsDto(
                ErrorCodes.LISTENER_ERROR,
                errorMsg,
                userId,
                className);
    }

    private static Long getUserIdFromPayLoad(Object payload) {
        return payload instanceof UserIdRqDto ? ((UserIdRqDto) payload).getUserId() : null;
    }

    private void sendToTelegram(String errorMsg, String payloadClasName, Long userId) {
        TelegramSender.getInstance().sendToTelegram(
                format("%s : %s" +
                                "\r\n\r\n%s" +
                                "\r\n\r\nuid: %d",
                        ErrorCodes.LISTENER_ERROR, payloadClasName, errorMsg, userId),
                teleToken, chatId);
    }

    public static String extractErrorMessage(Throwable e) {
        if (!(e.getCause() instanceof MethodArgumentNotValidException)) {
            String msg = String.join("\r\n", recursiveReadMessages(e, new ArrayList<>()));
            return msg.isEmpty() ? "Не известная ошибка!" : msg;
        } else {
            var firstError = Objects.requireNonNull(((MethodArgumentNotValidException) e.getCause()).getBindingResult())
                    .getAllErrors()
                    .get(0);
            return firstError.getDefaultMessage();
        }
    }
    private static List<String> recursiveReadMessages(Throwable e, List<String> list) {
        if (e != null && e.getMessage() != null) {
            list.add(e.getMessage());
            return recursiveReadMessages(e.getCause(), list);
        }
        return list;
    }
}
