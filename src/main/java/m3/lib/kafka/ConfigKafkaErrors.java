package m3.lib.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListenerConfigurer;
import org.springframework.kafka.config.KafkaListenerEndpointRegistrar;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ConfigKafkaErrors implements KafkaListenerConfigurer {

    //   @Bean
//    public KafkaListenerErrorHandler validationErrorHandler() {
//        return new KafkaErrorHandler();
//    }


//    @Autowired
//    private Validator validator;

    @Override
    public void configureKafkaListeners(KafkaListenerEndpointRegistrar registrar) {
        //    registrar.setValidator(this.validator);
    }

//
}