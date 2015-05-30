package calendar.caparso.es.calendar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import calendar.caparso.es.calendar.constant.CalendarMode;
import calendar.caparso.es.calendar.util.DateUtil;
import calendar.caparso.es.calendar.vo.EventVO;

class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

    private int calendarMode;

    private List<Date> dateList;

    public ScreenSlidePagerAdapter(FragmentManager fm, List<Date> dateList, int mode) {
        super(fm);
        this.dateList = dateList;
        this.calendarMode = mode;
    }

    @Override
    public Fragment getItem(int position) {
        List<EventVO> eventVOList = new ArrayList<EventVO>();
        EventVO eventVO = new EventVO();
        eventVO.setTitle("Title event");
        Calendar calendar = Calendar.getInstance();
        eventVO.setInitDate(calendar.getTime());
        calendar.add(Calendar.DATE, 1);
        eventVO.setEndDate(calendar.getTime());
        eventVOList.add(eventVO);
        return new ScreenSlidePageFragment().newInstance(eventVOList);
    }

    @Override
    public int getCount() {
        return dateList.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return DateUtil.getSimpleDateFormatByMode(getCalendarMode()).format(dateList.get(position));
    }

    /**
     * Add new list of days at the end of the actual list.
     *
     * @param dateList list of days to add.
     */
    public void addDates(List<Date> dateList) {
        addDates(getCount(), dateList);
    }

    /**
     * Add new list of days at a specific position of the actual list.
     *
     * @param position position to insert the new list.
     * @param dateList list of days to add.
     */
    public void addDates(Integer position, List<Date> dateList) {
        this.dateList.addAll(position, dateList);
        notifyDataSetChanged();
    }

    /**
     * Return the date of the specific position.
     *
     * @param position
     * @return date of the specific position.
     */
    public Date getDateByPosition(Integer position) {
        return dateList.get(position);
    }

    /*
    * GETTER AND SETTERS
    */

    public int getCalendarMode() {
        return calendarMode;
    }

    public void setCalendarMode(int mode) {
        this.calendarMode = mode;
    }

    public List<Date> getDateList() {
        return dateList;
    }

    public void setDateList(List<Date> dateList) {
        this.dateList = dateList;
    }
}