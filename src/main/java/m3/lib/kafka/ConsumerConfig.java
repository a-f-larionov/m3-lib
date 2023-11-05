package m3.lib.kafka;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.CommonLoggingErrorHandler;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Map;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

@Configuration
public class ConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;
    @Value("${spring.kafka.consumer.trusted_packages}")
    private String trustedPackages;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Bean
    public ConsumerFactory<String, Object> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(Map.of(
                BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress,
                KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class,
                GROUP_ID_CONFIG, "group_1",
                JsonDeserializer.TRUSTED_PACKAGES, trustedPackages),

                new StringDeserializer(),
                new ErrorHandlingDeserializer<>(
                        new JsonDeserializer<>())
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, Object>();

        factory.setConsumerFactory(consumerFactory());
        factory.setCommonErrorHandler(new CommonLoggingErrorHandler());
        factory.setReplyTemplate(kafkaTemplate);

        return factory;
    }
}