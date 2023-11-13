package m3.lib.helpers;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class TelegramSender {

    private static TelegramSender instance;
    private static final Object mutex = new Object();

    public static TelegramSender getInstance() {
        TelegramSender result = instance;
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null) {
                    instance = result = new TelegramSender();
                }
            }
        }
        return result;
    }

    private TelegramSender() {

    }

    public void sendToTelegram(String message, String token, String chatId) {
        message = prepareMessage(message);
        var endpoint = "https://api.telegram.org/bot"
                + token
                + "/sendMessage" +
                "?chat_id=" + chatId +
                "&text=" + URLEncoder.encode(message, StandardCharsets.UTF_8);

        endpoint = endpoint.substring(0, Math.min(endpoint.length(), 1024));

        ProcessBuilder pb = new ProcessBuilder();
        pb.command("curl", endpoint, "--ssl-no-revoke");
        Process p;
        try {
            p = pb.start();
            p.waitFor();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static String prepareMessage(String message) {
        // move to to some other place
        if (message.contains("Endpoint handler details:")) {
            message = message.substring(0, message.indexOf("Endpoint handler details:"));
        }
        message = message.replaceAll("([a-z])[a-z]+\\.([a-z])[a-z]+\\.", "$1.$2.");
//        message = message.replace("org.springframework.kafka.listener.", "o.s.k.l.");
//        message = message.replace("org.springframework.kafka.", "o.s.k.");
//        message = message.replace("org.springframework.", "o.s.");
        return message;
    }
}
