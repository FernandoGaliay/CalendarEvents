package calendar.caparso.es.calendar.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import calendar.caparso.es.calendar.constant.CalendarMode;

/**
 * Created by Fernando Galiay on 30/05/2015.
 */
public class DateUtil {

    /**
     * Provides a list of dates to init the calendar.
     *
     * @param daysIntervalSize
     * @return
     */
    public static List<Date> initDates(Integer daysIntervalSize) {
        List<Date> dateList = new ArrayList<Date>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -((daysIntervalSize / 2) + 1));
        for (int i = 0; i <= daysIntervalSize; i++) {
            calendar.add(Calendar.DATE, 1);
            dateList.add(calendar.getTime());
        }
        return dateList;
    }

    /**
     * Provides a list of dates to add before the fist day of the actual list.
     *
     * @param daysIntervalSize Amount of days that are going to add to the list.
     * @param firstDate        Date of the first element of the date list.
     * @return List of dates to add before the fist day of the actual list.
     */
    public static List<Date> previousDates(Integer daysIntervalSize, Date firstDate) {
        List<Date> dateList = new ArrayList<Date>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(firstDate);
        for (int i = daysIntervalSize; i > 0; i--) {
            calendar.setTime(firstDate);
            calendar.add(Calendar.DATE, -i);
            dateList.add(calendar.getTime());
        }
        return dateList;
    }

    /**
     * Provides a list of dates to add after the last day of the actual list.
     *
     * @param daysIntervalSize Amount of days that are going to add to the list.
     * @param lastDate         Date of the last element of the date list.
     * @return List of dates to add after the last day of the actual list.
     */
    public static List<Date> nextDates(Integer daysIntervalSize, Date lastDate) {
        List<Date> dateList = new ArrayList<Date>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lastDate);
        for (int i = 1; i <= daysIntervalSize; i++) {
            calendar.add(Calendar.DATE, 1);
            dateList.add(calendar.getTime());
        }
        return dateList;
    }

    public static SimpleDateFormat getHourFormat(){
        return new SimpleDateFormat("HH:mm");
    }

    public static SimpleDateFormat getDayFormat(){
        return new SimpleDateFormat("EEE dd");
    }

    /**
     * Return a new instance of SimpleDateFormat object. Date format is set with mode parameter. DAY = EEE dd, WEEK = dd/MM, MONTH = MMM yyyy.
     *
     * @param mode Mode of the calendar.
     * @return New instance of SimpleDateFormat object.
     */
    public static SimpleDateFormat getSimpleDateFormatByMode(int mode) {
        SimpleDateFormat simpleDateFormat = null;
        if (CalendarMode.DAY == mode) {
            simpleDateFormat = new SimpleDateFormat("EEE dd");
        } else if (CalendarMode.WEEK == mode) {
            simpleDateFormat = new SimpleDateFormat("dd/MM");
        } else {
            simpleDateFormat = new SimpleDateFormat("MMM yyyy");
        }
        return simpleDateFormat;
    }
}
