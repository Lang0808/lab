package org.luke.common.util;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {

    public static String formatTime(ZonedDateTime zoneDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss.SSS xxx");
        return zoneDateTime.format(formatter);
    }

    public static String getAndFormatCurrentTime() {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
        return formatTime(now);
    }
}
