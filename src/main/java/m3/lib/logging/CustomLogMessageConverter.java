package m3.lib.logging;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.LoggingEvent;

import java.util.ArrayList;
import java.util.List;

public class CustomLogMessageConverter extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent event) {
        String msg = event.getMessage();
        if (event.getThrowableProxy() != null) {
            if (event instanceof LoggingEvent) {
                msg += extractErrorMessage(event.getThrowableProxy());
            }
        }
        msg = compactNamespaces(msg);
        return msg;
    }

    private static String compactNamespaces(String msg) {
        msg = msg.replaceAll("([a-z])[a-z]+\\.([a-z])[a-z]+\\.", "$1.$2.");
        return msg;
    }

    private static String extractErrorMessage(IThrowableProxy e) {
        String msg = String.join("\r\n", recursiveReadMessages(e, new ArrayList<>()));
        return msg.isEmpty() ? "Undefined error" : msg;
    }

    private static List<String> recursiveReadMessages(IThrowableProxy e, List<String> list) {
        if (e != null && e.getMessage() != null) {
            list.add(e.getMessage());
            recursiveReadMessages(e.getCause(), list);
        }
        return list;
    }
}
