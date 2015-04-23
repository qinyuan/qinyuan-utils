package com.qinyuan15.utils;

import org.junit.Test;

import java.sql.Date;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test DateUtils
 * Created by qinyuan on 15-1-11.
 */
public class DateUtilsTest {
    @Test
    public void testNewDate() throws Exception {
        long milliSecondsOfOneDay = 3600 * 24 * 1000;

        Date date = DateUtils.newDate("2011-2-28");
        assertThat(date.toString()).isEqualTo("2011-02-28");
        date.setTime(date.getTime() + milliSecondsOfOneDay); // add one day
        assertThat(date.toString()).isEqualTo("2011-03-01");

        date.setTime(date.getTime() + milliSecondsOfOneDay * 364);
        assertThat(date.toString()).isEqualTo("2012-02-28");
        date.setTime(date.getTime() + milliSecondsOfOneDay);
        assertThat(date.toString()).isEqualTo("2012-02-29");
    }

    @Test
    public void testGetCurrentHour() {
        assertThat(DateUtils.currentHour()).isEqualTo(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
    }

    @Test
    public void testNow() throws Exception {
        assertThat(DateUtils.now().getTime() - System.currentTimeMillis()).isGreaterThan(-2);
    }

    @Test
    public void testToLongString() throws Exception {
        Date date = DateUtils.newDate("2012-12-12");
        assertThat(DateUtils.toLongString(date)).isEqualTo("2012-12-12 00:00:00");
    }

    @Test
    public void testThreeMonthAgo() throws Exception {
        Date date = DateUtils.threeMonthsAgo();
        assertThat(System.currentTimeMillis() - 1000L * 3600 * 24 * 90 - date.getTime())
                .isLessThan(50).isGreaterThan(-50);
    }

    @Test
    public void testGetDayDiff() throws Exception {
        Date date1 = DateUtils.newDate("2012-02-28");
        Date date2 = DateUtils.newDate("2012-03-26");
        assertThat(DateUtils.getDayDiff(date1, date2)).isEqualTo(27);

        Date date3 = DateUtils.newDate("2013-02-28");
        assertThat(DateUtils.getDayDiff(date1, date3)).isEqualTo(366);
    }

    @Test
    public void testIsDate() throws Exception {
        assertThat(DateUtils.isDate("1022-12-12")).isTrue();
        assertThat(DateUtils.isDate("1012-1212")).isFalse();
    }

    @Test
    public void testIsDateTime() throws Exception {
        assertThat(DateUtils.isDateTime("1012-01-01 12:13:40")).isTrue();
        assertThat(DateUtils.isDateTime("1012-01-01 12:13:404")).isFalse();
    }

    @Test
    public void testGetDatePart() throws Exception {
        assertThat(DateUtils.getDatePart("2012-12-12 10:10:10")).isEqualTo("2012-12-12");
    }
}
