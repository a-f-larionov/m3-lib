package m3.lib;


import m3.lib.store.ProfilerAop;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {
        ProfilerDemoClass.class,
        ProfilerAop.class,
        AopAutoConfiguration.class})
public class ProfilerAopTest {

    @Autowired
    ProfilerDemoClass profilerDemoClass;

    @Test
    public void test() {
        // given
        String str = "str";

        // when
        String result = profilerDemoClass.demoMethod("str");

        // then
        assertThat(result).isEqualTo(str);
        assertThat(ProfilerAop.data.size()).isEqualTo(1L);
    }
}
