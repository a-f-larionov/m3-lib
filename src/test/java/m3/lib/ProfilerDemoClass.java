package m3.lib;

import org.springframework.stereotype.Component;


@ProfileMethods
@Component
public class ProfilerDemoClass {

    public String demoMethod(String str) {
        return str;
    }
}
