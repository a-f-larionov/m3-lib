package m3.lib.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.KafkaListenerEndpointRegistrar;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class KafkaListenerConfigurer implements org.springframework.kafka.annotation.KafkaListenerConfigurer {

    @Autowired
    private Validator validator;

    @Override
    public void configureKafkaListeners(KafkaListenerEndpointRegistrar registrar) {
        System.out.println("configurer");

        registrar.setValidator(new Validator() {
            @Override
            public boolean supports(Class<?> clazz) {
                System.out.println("supports");
                var ret = validator.supports(clazz);
                return ret;
            }

            @Override
            public void validate(Object target, Errors errors) {
                System.out.println("validate");
                validator.validate(target, errors);
                System.out.println("validate");
            }
        });
    }
}