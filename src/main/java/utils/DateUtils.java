package utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Currency;

public class DateUtils {

    private static WeekFields getWeekFields(Currency currency) {
        if (CurrencyUtils.isSundayStartCurrency(currency)) {
            return WeekFields.SUNDAY_START;
        } else {
            return WeekFields.ISO;
        }
    }

    public static boolean isWeekEnd(LocalDate date, Currency currency) {
        return isWeekEnd(date, getWeekFields(currency).getFirstDayOfWeek());
    }

    public static LocalDate nextWorkingDay(LocalDate date, Currency currency) {
        return nextWorkingDay(date, getWeekFields(currency).getFirstDayOfWeek());
    }

    public static LocalDate nextWorkingDay(LocalDate date, DayOfWeek startOfWeek) {
        int numberOfDaysToNextWorkingDay = 1;
        if (isWeekEnd(date, startOfWeek)) {
            numberOfDaysToNextWorkingDay = startOfWeek.getValue() - date.getDayOfWeek().getValue();
        }
        if (numberOfDaysToNextWorkingDay < 0) {
            numberOfDaysToNextWorkingDay += 7;
        }
        return date.plusDays(numberOfDaysToNextWorkingDay);
    }

    private static boolean isWeekEnd(LocalDate date, DayOfWeek firstDayOfWeek) {
        if (firstDayOfWeek == DayOfWeek.MONDAY) {
            return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
        } else {
            return date.getDayOfWeek() == DayOfWeek.FRIDAY || date.getDayOfWeek() == DayOfWeek.SATURDAY;
        }
    }

}
