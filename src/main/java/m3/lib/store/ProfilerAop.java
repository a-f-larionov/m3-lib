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

    @Pointcut("@within(m3.lib.ProfileThis)")
    public void profileThis() {
    }

    @Around("profileThis()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();
        var methodName = joinPoint.getSignature().getName();

        data.putIfAbsent(methodName, new AtomicInteger(0));
        data.get(methodName).incrementAndGet();

        return proceed;
    }
}
