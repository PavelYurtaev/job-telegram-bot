package ru.pavelyurtaev.jobbot.util;

import java.util.Arrays;
import java.util.List;

public class StringUtils {

    public static boolean isNullOrBlank(final String string) {
        return string == null || string.isBlank();
    }

    public static boolean isNotNullAndNotBlank(final String string) {
        return string != null && !string.isBlank();
    }

    public static boolean isAllNotBlank(final String... strings) {
        for (final String string : strings) {
            if (isNullOrBlank(string)) {
                return false;
            }
        }
        return true;
    }

    // delimiter ;
    public static String joinArrayToString(final String... strings) {
        return String.join(";", strings);
    }    // delimiter ;

    public static String joinArrayToString(final Integer... integers) {
        return joinArrayToString(Arrays.stream(integers).map(String::valueOf).toArray(String[]::new));
    }

    public static List<String> splitStringToArray(final String string) {
        return Arrays.stream(string.split(";")).toList();
    }

    /*
        Examples

        /start argument
        /image argument

        returns argument
        returns empty string if no argument
     */
    public static String getCommandArgument(final String command) {
        final String[] strings = command == null ? new String[0] : command.split(" ");
        if (strings.length > 1 && !strings[1].isBlank()) {
            return strings[1];
        } else {
            return "";
        }
    }
}
