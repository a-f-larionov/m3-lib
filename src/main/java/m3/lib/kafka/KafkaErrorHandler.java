package m3.lib.kafka;

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
        String errorMsg;
        var payload = message.getPayload();

        errorMsg = extractErrorMessage(exception);
        errorMsg += "\r\n\r\n--- PAYLOAD ---\r\n" + payload.toString().replace(", ", ",\r\n");

        if (payload instanceof UserIdRqDto && ((UserIdRqDto) payload).getUserId() != null) {
            var rs = new ErrorRsDto(
                    ErrorCodes.VALIDATE_ERROR,
                    errorMsg,
                    ((UserIdRqDto) payload).getUserId(),
                    payload.getClass().getName());
            kafkaTemplate.send("topic-client", rs);
            TelegramSender.getInstance().sendToTelegram(
                    format("%s : %s" +
                            "\r\n\r\n%s" +
                            "\r\n\r\nuid: %d", rs.getErrorCodes(), rs.getPacketFrom(), errorMsg, rs.getUserId()), teleToken, chatId);
        }
        exception.printStackTrace(System.out);
        return null;
    }

    private static String extractErrorMessage(ListenerExecutionFailedException e) {
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
