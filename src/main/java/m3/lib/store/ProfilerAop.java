package m3.lib.store;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Aspect
public class ProfilerAop {

    public static Map<String, AtomicInteger> data = new HashMap<>();

    //@todo @PrfileThis -> @ProfileMethods
    @Pointcut("@within(m3.lib.ProfileMethods)")
    public void profileMethods() {
    }

    @Around("profileMethods()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
//        @todo test it. Object proceed = joinPoint.proceed();
        var methodName = joinPoint.getSignature().getName();
        // @todo use profilerReporter.putOne();
        data.putIfAbsent(methodName, new AtomicInteger(0));
        data.get(methodName).incrementAndGet();

        return joinPoint.proceed();
    }
}
