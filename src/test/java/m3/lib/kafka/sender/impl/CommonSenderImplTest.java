package m3.lib.kafka.sender.impl;

import m3.lib.dto.rq.StatisticRqDto;
import m3.lib.enums.StatisticEnum;
import m3.lib.kafka.sender.CommonSender;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CommonSenderImplTest {
    @Mock
    KafkaTemplate kafkaTemplate;
    @InjectMocks
    CommonSenderImpl commonSender;

    @Test
    @SuppressWarnings("unchecked")
    void statistic() {
        // given
        Long userId = 123L;
        StatisticEnum statEnum = StatisticEnum.ID_LEVEL_UP;
        String param1 = "msg1";
        String param2 = "msg2";

        // when
        commonSender.statistic(userId, statEnum, param1, param2);

        // then
        var argumentCaptor = ArgumentCaptor.forClass(StatisticRqDto.class);

        verify(kafkaTemplate)
                .send(eq("topic-common"), argumentCaptor.capture());

        StatisticRqDto value = argumentCaptor.getValue();

        assertThat(value.getUserId()).isEqualTo(userId);
        assertThat(value.getParamA()).isEqualTo(param1);
        assertThat(value.getParamB()).isEqualTo(param2);
        assertThat(value.getStatId()).isEqualTo(statEnum);
        assertThat(value.getTime()).isCloseTo(System.currentTimeMillis(), Offset.offset(10000L));
    }
}