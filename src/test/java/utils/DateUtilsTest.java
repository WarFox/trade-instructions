package utils;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class DateUtilsTest {

    final static Currency GBP = Currency.getInstance(Locale.UK);
    final static Currency AED = Currency.getInstance("AED");
    final static Currency SAR = Currency.getInstance("SAR");

    @Test
    public void testMondayIsNotWeekEnd() throws Exception {
        LocalDate date = getDateByDay(DayOfWeek.MONDAY);
        assertThat(DateUtils.isWeekEnd(date, GBP)).isFalse();
    }

    @Test
    public void testTuesdayIsNotWeekEnd() throws Exception {
        LocalDate date = getDateByDay(DayOfWeek.TUESDAY);
        assertThat(DateUtils.isWeekEnd(date, GBP)).isFalse();
    }

    @Test
    public void testWednesdayIsNotWeekEnd() throws Exception {
        LocalDate date = getDateByDay(DayOfWeek.WEDNESDAY);
        assertThat(DateUtils.isWeekEnd(date, GBP)).isFalse();
    }

    @Test
    public void testThursdayIsNotWeekEnd() throws Exception {
        LocalDate date = getDateByDay(DayOfWeek.THURSDAY);
        assertThat(DateUtils.isWeekEnd(date, GBP)).isFalse();
    }

    @Test
    public void testFridayIsNotWeekEnd() throws Exception {
        LocalDate date = getDateByDay(DayOfWeek.FRIDAY);
        assertThat(DateUtils.isWeekEnd(date, GBP)).isFalse();
    }

    @Test
    public void testSaturdayIsWeekEnd() throws Exception {
        LocalDate date = getDateByDay(DayOfWeek.SATURDAY);
        assertThat(DateUtils.isWeekEnd(date, GBP)).isTrue();
    }

    @Test
    public void testSundayIsWeekEnd() throws Exception {
        LocalDate date = getDateByDay(DayOfWeek.SUNDAY);
        assertThat(DateUtils.isWeekEnd(date, GBP)).isTrue();
    }

    @Test
    public void testSundayIsNotWeekEndForAED() throws Exception {
        LocalDate date = getDateByDay(DayOfWeek.SUNDAY);
        assertThat(DateUtils.isWeekEnd(date, AED)).isFalse();
    }

    @Test
    public void testSundayIsNotWeekEndForSAR() throws Exception {
        LocalDate date = getDateByDay(DayOfWeek.SUNDAY);
        assertThat(DateUtils.isWeekEnd(date, SAR)).isFalse();
    }
    @Test
    public void testFridayIsWeekEndForAED() throws Exception {
        LocalDate date = getDateByDay(DayOfWeek.FRIDAY);
        assertThat(DateUtils.isWeekEnd(date, AED)).isTrue();
    }

    @Test
    public void testFridayIsWeekEndForSAR() throws Exception {
        LocalDate date = getDateByDay(DayOfWeek.FRIDAY);
        assertThat(DateUtils.isWeekEnd(date, SAR)).isTrue();
    }

    @Test
    public void testNextWorkingDayWithWeekStartsOnMonday() throws Exception {
        LocalDate date = getDateByDay(DayOfWeek.SATURDAY);
        assertThat(date).isEqualTo(LocalDate.of(2017, 1, 7));
        LocalDate nextWorkingDay = DateUtils.nextWorkingDay(date, DayOfWeek.MONDAY);
        assertThat(nextWorkingDay).isEqualTo(LocalDate.of(2017, 1, 9));
        assertThat(nextWorkingDay.getDayOfWeek()).isEqualTo(DayOfWeek.MONDAY);
    }

    @Test
    public void testNextWorkingDayWithWeekStartOnSunday() throws Exception {
        LocalDate date = getDateByDay(DayOfWeek.FRIDAY);
        assertThat(date).isEqualTo(LocalDate.of(2017, 1, 6));
        LocalDate nextWorkingDay = DateUtils.nextWorkingDay(date, DayOfWeek.SUNDAY);
        assertThat(nextWorkingDay).isEqualTo(LocalDate.of(2017, 1, 8));
        assertThat(nextWorkingDay.getDayOfWeek()).isEqualTo(DayOfWeek.SUNDAY);
    }

    @Test
    public void testNextWorkingDayFromMidweekWithWeekStartsOnMonday() throws Exception {
        LocalDate date = getDateByDay(DayOfWeek.WEDNESDAY);
        assertThat(date).isEqualTo(LocalDate.of(2017, 1, 4));
        LocalDate nextWorkingDay = DateUtils.nextWorkingDay(date, DayOfWeek.MONDAY);
        assertThat(nextWorkingDay).isEqualTo(LocalDate.of(2017, 1, 5));
        assertThat(nextWorkingDay.getDayOfWeek()).isEqualTo(DayOfWeek.THURSDAY);
    }

    @Test
    public void testNextWorkingDayFromMidweekWithWeekStartsOnSunday() throws Exception {
        LocalDate date = getDateByDay(DayOfWeek.WEDNESDAY);
        assertThat(date).isEqualTo(LocalDate.of(2017, 1, 4));
        LocalDate nextWorkingDay = DateUtils.nextWorkingDay(date, DayOfWeek.SUNDAY);
        assertThat(nextWorkingDay).isEqualTo(LocalDate.of(2017, 1, 5));
        assertThat(nextWorkingDay.getDayOfWeek()).isEqualTo(DayOfWeek.THURSDAY);
    }

    private LocalDate getDateByDay(DayOfWeek day) {
        LocalDate date = LocalDate.of(2017, 1, 1);
        int dayOfWeek = date.getDayOfWeek().getValue();
        int dayValue = day.getValue();
        return date.plusDays(7 - dayOfWeek + dayValue);
    }

}
