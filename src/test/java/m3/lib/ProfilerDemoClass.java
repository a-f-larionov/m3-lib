package m3.lib;

import org.springframework.stereotype.Component;


@ProfileThis
@Component
public class ProfilerDemoClass {

    public String demoMethod(String str) {
        return str;
    }
}
