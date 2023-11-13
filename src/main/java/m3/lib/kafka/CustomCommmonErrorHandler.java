package m3.lib.kafka;

import lombok.RequiredArgsConstructor;
import m3.lib.helpers.TelegramSender;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.listener.CommonLoggingErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;

@RequiredArgsConstructor
public class CustomCommmonErrorHandler extends CommonLoggingErrorHandler {

    private final String teleToken;
    private final String chatId;

    @Override
    public boolean handleOne(Exception thrownException, ConsumerRecord<?, ?> record, Consumer<?, ?> consumer, MessageListenerContainer container) {
        sendToTelegram(thrownException);
        return super.handleOne(thrownException, record, consumer, container);
    }

    @Override
    public void handleBatch(Exception thrownException, ConsumerRecords<?, ?> data, Consumer<?, ?> consumer, MessageListenerContainer container, Runnable invokeListener) {
        sendToTelegram(thrownException);
        super.handleBatch(thrownException, data, consumer, container, invokeListener);
    }

    @Override
    public void handleOtherException(Exception thrownException, Consumer<?, ?> consumer, MessageListenerContainer container, boolean batchListener) {
        sendToTelegram(thrownException);
        super.handleOtherException(thrownException, consumer, container, batchListener);
    }

    private void sendToTelegram(Exception thrownException) {

        String msg = KafkaErrorHandler.extractErrorMessage(thrownException);
        TelegramSender.getInstance().sendToTelegram(msg, teleToken, chatId);

    }
}
