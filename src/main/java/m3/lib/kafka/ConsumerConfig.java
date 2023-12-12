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
import org.springframework.kafka.listener.CommonErrorHandler;
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
    @Value("${spring.kafka.topicName}")
    private String topicName;
    @Value("${alerter.telegram.token}")
    private String teleToken;
    @Value("${alerter.telegram.chatId}")
    private String chatId;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private CommonErrorHandler commonErrorHandler;

    @Bean
    public ConsumerFactory<String, Object> consumerFactory() {
        System.out.println("!!!!!ConsumerFactory" + bootstrapAddress);
        return new DefaultKafkaConsumerFactory<>(Map.of(
                BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress,
                KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class,
                GROUP_ID_CONFIG, "group_1",
                CLIENT_ID_CONFIG, topicName,
                AUTO_OFFSET_RESET_CONFIG, "earliest",
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
        factory.setCommonErrorHandler(commonErrorHandler);
        factory.setReplyTemplate(kafkaTemplate);

        return factory;
    }

}