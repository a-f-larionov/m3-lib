package m3.lib.kafka;

import lombok.RequiredArgsConstructor;
import m3.lib.commons.ErrorCodes;
import m3.lib.dto.rq.UserIdRqDto;
import m3.lib.dto.rs.ErrorRsDto;
import m3.lib.helpers.TelegramSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@RequiredArgsConstructor
@Component
public class KafkaListenerErrorHandler implements org.springframework.kafka.listener.KafkaListenerErrorHandler {

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

        String errorMsg = createErrorMessage(payload);
        sendToTelegram(errorMsg, payloadClassName, userId);

        if (userId != null) {
            sendToTopicClient(errorMsg, payloadClassName, userId);
        }
        // essentially sendToTelegramm
        throw exception;
    }

    private void sendToTopicClient(String errorMsg, String payloadClassName, Long userId) {
        kafkaTemplate.send(TopicNames.CLIENT, createErrorRsDto(errorMsg, payloadClassName, userId));
    }

    private static String createErrorMessage(Object payload) {
        return "\r\n\r\n--- PAYLOAD ---\r\n" + payload.toString().replace(", ", ",\r\n");
    }

    private static ErrorRsDto createErrorRsDto(String errorMsg, String className, Long userId) {
        return new ErrorRsDto(
                ErrorCodes.LISTENER_ERROR,
                errorMsg,
                userId,
                className);
    }

    private void sendToTelegram(String errorMsg, String payloadClasName, Long userId) {
        TelegramSender.getInstance().sendToTelegram(
                format("%s : %s" +
                                "\r\n\r\n%s" +
                                "\r\n\r\nuid: %d",
                        ErrorCodes.LISTENER_ERROR, payloadClasName, errorMsg, userId),
                teleToken, chatId);
    }

    private static Long getUserIdFromPayLoad(Object payload) {
        return payload instanceof UserIdRqDto ? ((UserIdRqDto) payload).getUserId() : null;
    }
}
