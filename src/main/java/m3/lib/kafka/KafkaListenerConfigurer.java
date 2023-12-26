package m3.lib.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.KafkaListenerEndpointRegistrar;
import org.springframework.validation.Validator;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class KafkaListenerConfigurer implements org.springframework.kafka.annotation.KafkaListenerConfigurer {

    @Autowired
    private Validator validator;

    @Override
    public void configureKafkaListeners(KafkaListenerEndpointRegistrar registrar) {
        registrar.setValidator(validator);
    }
}