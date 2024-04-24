package m3.lib.kafka.sender.impl;

import lombok.RequiredArgsConstructor;
import m3.lib.dto.rq.LogRqDto;
import m3.lib.dto.rq.StatisticRqDto;
import m3.lib.enums.ClientLogLevels;
import m3.lib.enums.StatisticEnum;
import m3.lib.kafka.TopicNames;
import m3.lib.kafka.sender.CommonSender;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommonSenderImpl implements CommonSender {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void statistic(Long userId, StatisticEnum statisticEnum, String paramA, String paramB) {
        kafkaTemplate.send(TopicNames.COMMON, StatisticRqDto.builder()
                .userId(userId)
                .statId(statisticEnum)
                .time(System.currentTimeMillis())
                .paramA(paramA)
                .paramB(paramB)
                .build());
    }

    @Override
    public void log(Long userId, String message, ClientLogLevels level, boolean sendToTelegram) {
        kafkaTemplate.send(TopicNames.COMMON, LogRqDto.builder()
                .level(level)
                .userId(userId)
                .message(message)
                .sendToTelegram(sendToTelegram)
                .build());
    }
}
