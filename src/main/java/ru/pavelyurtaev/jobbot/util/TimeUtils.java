package ru.pavelyurtaev.jobbot.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtils {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static String timestampToString(final LocalDateTime timestamp) {
        return timestamp.format(formatter);
    }
}
