package utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;

public class DateUtils {
    private final DayOfWeek startOfWeek;
    private final WeekFields weekFields;

    public DateUtils(DayOfWeek startOfWeek) {
        this.startOfWeek = startOfWeek;
        this.weekFields = getWeekFields(startOfWeek);
    }

    private static WeekFields getWeekFields(DayOfWeek startOfWeek) {
        if (startOfWeek == DayOfWeek.SUNDAY) {
            return WeekFields.SUNDAY_START;
        } else {
            return WeekFields.ISO;
        }
    }

    public static int[] weekEnd(DayOfWeek startOfWeek) {
        int[] weekEnds = new int[2];
        int value = startOfWeek.getValue();
        weekEnds[0] = 7 - value;
        weekEnds[1] = 7 - value - 1;
        return weekEnds;
    }

    public static boolean isWeekEnd(LocalDate date) {
        return isWeekEnd(date, DayOfWeek.MONDAY);
    }

    public static boolean isWeekEnd(LocalDate date, DayOfWeek startOfWeek) {
        if (startOfWeek == DayOfWeek.MONDAY) {
            return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
        } else {
            return date.getDayOfWeek() == DayOfWeek.FRIDAY || date.getDayOfWeek() == DayOfWeek.SATURDAY;
        }
    }

    public static LocalDate nextWorkingDay(LocalDate date, DayOfWeek startOfWeek) {
        int numberOfDaysToNextWorkingDay = 1;
        if (isWeekEnd(date, startOfWeek)) {
            numberOfDaysToNextWorkingDay = startOfWeek.getValue() - date.getDayOfWeek().getValue();
        }
        if(numberOfDaysToNextWorkingDay < 0) {
            numberOfDaysToNextWorkingDay += 7;
        }
        return date.plusDays(numberOfDaysToNextWorkingDay);
    }

}
