package m3.lib.helpers;

import m3.lib.entities.UserEntity;
import m3.lib.settings.CommonSettings;

public class UserHelper {

    public static Long getHealths(UserEntity user) {
        Long fullRecoveryTime = user.getFullRecoveryTime();
        Long now = (long) Math.floor((double) System.currentTimeMillis() / 1000);
        Long recoveryTime = CommonSettings.HEALTH_RECOVERY_TIME;

        Long timeLeft = fullRecoveryTime - now;

        if (timeLeft <= 0) return CommonSettings.HEALTH_MAX;

        return Math.max(
                0L,
                (CommonSettings.HEALTH_MAX - ceil(timeLeft, recoveryTime))
        );
    }

    private static long ceil(double timeLeft, Long recoveryTime) {
        return (long) Math.ceil(timeLeft / recoveryTime);
    }

    public static void setMaxHealth(UserEntity user) {
        user.setFullRecoveryTime((long) Math.floor((double) System.currentTimeMillis() / 1000));
    }
}
