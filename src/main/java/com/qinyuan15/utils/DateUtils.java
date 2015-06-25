package com.qinyuan15.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Tool class about date
 * Created by qinyuan on 15-1-5.
 */
public class DateUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);

    private DateUtils() {
    }

    /**
     * create Date Object by String such as '2000-12-01'
     *
     * @param dateStr date String such as '2000-12-01'
     * @return a {@link java.sql.Date} instance
     */
    public static Date newDate(String dateStr) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return new Date(dateFormat.parse(dateStr).getTime());
        } catch (ParseException e) {
            LOGGER.error("error in parsing date String '{}': {}", dateStr, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Convert date value to long style string such as "2015-01-01 12:12:12"
     *
     * @param date date value to convert
     * @return long style date string
     */
    public static String toLongString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    public static int currentHour() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }

    public static Date now() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * @return a string represent current time such as "2015-06-26 00:59:23"
     */
    public static String nowString() {
        return toLongString(now());
    }

    public static String todayStartTime() {
        return now().toString() + " 00:00:00";
    }

    public static String todayEndTime() {
        return now().toString() + " 23:59:59";
    }

    public static boolean isDate(String date) {
        return date != null && date.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$");
    }

    public static boolean isDateTime(String dateTime) {
        return dateTime != null &&
                dateTime.matches("^\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}$");
    }

    public static boolean isDateOrDateTime(String value) {
        return isDate(value) || isDateTime(value);
    }

    /**
     * calculate the days between first date and second date
     *
     * @param date1 first date
     * @param date2 second date
     * @return a number greater than zero if date1 and is earlier than date2
     */
    public static int getDayDiff(Date date1, Date date2) {
        long timeStampDiff = date2.getTime() - date1.getTime();
        return (int) Math.round(timeStampDiff * 1.0 / (24 * 3600 * 1000));
    }

    public static Date threeDaysAgo() {
        long secondsOfThreeDays = 3 * 3600 * 24;
        return new Date(System.currentTimeMillis() - secondsOfThreeDays * 1000);
    }

    public static Date threeMonthsAgo() {
        long secondsOfThreeMonths = 90 * 3600 * 24;
        return new Date(System.currentTimeMillis() - secondsOfThreeMonths * 1000);
    }

    /**
     * Adjust date String queried from database directly
     * <p>
     * In MySQL, the date string queried from database looks like
     * "2015-01-01 15:15:15.5", we need to convert it to format such as
     * "2015-01-01 15:15:15"
     * </p>
     *
     * @param dateString date string queried from MySQL
     * @return adjusted date string
     */
    public static String adjustDateStringFromDB(String dateString) {
        return dateString == null ? null : dateString.replaceAll("\\.\\d*$", "");
    }

    public static String getDatePart(String dateTime) {
        return dateTime == null ? null : dateTime.replaceAll("\\s.*$", "");
    }
}
