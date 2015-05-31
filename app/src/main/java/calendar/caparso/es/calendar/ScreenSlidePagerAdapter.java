package calendar.caparso.es.calendar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import calendar.caparso.es.calendar.util.DateUtil;
import calendar.caparso.es.calendar.vo.Event;

class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

    /**
     * Mode of the calendar: day, week and month.
     */
    private int calendarMode;

    /**
     * List of the date loaded.
     */
    private List<Date> dateList;

    /**
     * Map to reload events when the fragment is recreate.
     */
    private HashMap<Integer, List<? extends Event>> dateEventsHashMap;

    /**
     * Save the reference to retrieve the right fragment from FragmentPagerAdapter.
     */
    private SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();

    /**
     * Default constructor.
     *
     * @param fm       FragmentManager object.
     * @param dateList List of the date pages in the adapter.
     * @param mode     Mode of the calendar.
     */
    public ScreenSlidePagerAdapter(FragmentManager fm, List<Date> dateList, int mode) {
        super(fm);
        this.dateList = dateList;
        this.calendarMode = mode;
        this.dateEventsHashMap = new HashMap<Integer, List<? extends Event>>();
    }

    /**
     * @param position
     * @return
     */
    @Override
    public SlideDayFragment getItem(int position) {
        return new SlideDayFragment().newInstance(getEventFromMap(position));
    }

    /**
     * Add new fragment to the registered fragments list.
     *
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    /**
     * Clean the reference of the fragment in the registered fragments list.
     *
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    /**
     * Get the fragment that is related to the position in the registered fragments list.
     *
     * @param position
     * @return
     */
    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }

    /**
     * Amount of pages of the Viewpager.
     *
     * @return
     */
    @Override
    public int getCount() {
        return dateList.size();
    }

    /**
     * @param object
     * @return
     */
    @Override
    public int getItemPosition(Object object) {
        return POSITION_UNCHANGED;
    }

    /**
     * Return the string of the viewpager page title.
     *
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return DateUtil.getSimpleDateFormatByMode(getCalendarMode()).format(dateList.get(position)).toUpperCase();
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
    * Get calendar mode
    */

    public int getCalendarMode() {
        return calendarMode;
    }

    /**
     * Set calendar mode.
     *
     * @param mode Integer that represents mode of the calendar.
     */
    public void setCalendarMode(int mode) {
        this.calendarMode = mode;
    }

    /**
     * Get the list of the dates.
     *
     * @return List of the dates in the FragmentPagerAdapter.
     */
    public List<Date> getDateList() {
        return dateList;
    }

    /**
     * Set the list of the dates.
     *
     * @param dateList
     */
    public void setDateList(List<Date> dateList) {
        this.dateList = dateList;
    }

    public void saveEventsInMap(List<? extends Event> events, Integer position) {
        dateEventsHashMap.put(position, events);
    }

    private List<? extends Event> getEventFromMap(int position) {
        List<? extends Event> events = dateEventsHashMap.get(position);
        if (events == null) {
            events = new ArrayList<Event>();
        }
        return events;
    }
}