package m3.lib.kafka.sender;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CustomCommonErrorHandler implements org.springframework.kafka.listener.CommonErrorHandler {

    @Override
    public boolean handleOne(Exception thrownException, ConsumerRecord<?, ?> record, Consumer<?, ?> consumer, MessageListenerContainer container) {
        return CommonErrorHandler.super.handleOne(thrownException, record, consumer, container);
    }
}
