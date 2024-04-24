package m3.lib.kafka;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Map;

@Configuration
public class TopicNames implements EnvironmentAware {
    public final static String GAME = "topic-game";
    public final static String COMMON = "topic-common";
    public final static String USERS = "topic-users";
    public final static String CLIENT = "topic-client";
    public static String currentTopic = null;

    public static Map<String, String> serviceTopics = Map.of(
            "game-service", GAME,
            "common-service", COMMON,
            "users-service", USERS
    );

    @Override
    public void setEnvironment(Environment environment) {
        String springApplicationNameProperty = "spring.application.name";
        if (!environment.containsProperty(springApplicationNameProperty)) {
            throw new RuntimeException("Please set: " + springApplicationNameProperty);
        }
        currentTopic = getByAppName(environment.getProperty(springApplicationNameProperty));
    }

    private static String getByAppName(String appName) {
        return serviceTopics.get(appName);
    }
}
