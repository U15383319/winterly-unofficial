package winterly.util;

import java.time.LocalDate;
import java.time.Month;

public final class HolidayUtils {
    public static boolean isWinter() {
        Month month = LocalDate.now().getMonth();
        return month == Month.DECEMBER || month == Month.JANUARY || month == Month.FEBRUARY;
    }

    private HolidayUtils() {
    }
}
