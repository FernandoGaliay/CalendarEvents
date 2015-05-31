package calendar.caparso.es.calendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.viewpagerindicator.TitlePageIndicator;

import java.util.Date;
import java.util.List;

import calendar.caparso.es.calendar.util.DateUtil;
import calendar.caparso.es.calendar.vo.Event;

/**
 * Created by Fernando Galiay on 30/05/2015.
 */
public class CalendarEventView extends LinearLayout implements ViewPager.OnPageChangeListener {

    /**
     * Mode of the calendar: day, week and month.
     */
    private int dateMode;

    /**
     * Amount of dates to load when limit is reached.
     */
    private int dateIntervalSize;

    /**
     * Pager of the views. Related to list of dates.
     */
    private ViewPager viewPager;

    /**
     * Title indicator for the selected date.
     */
    private TitlePageIndicator titlePageIndicator;

    /**
     * Adapter for the ViewPager.
     */
    private ScreenSlidePagerAdapter screenSlidePagerAdapter;

    /**
     * Listener for the onDateSelected event.
     */
    private OnDateSelectedListener onDateSelectedListener;

    /**
     * Default constructor.
     *
     * @param context instance of FragmentActivity
     */
    public CalendarEventView(Context context) {
        this(context, null);
    }

    /**
     * Default constructor.
     *
     * @param context instance of FragmentActivity
     * @param attrs
     */
    public CalendarEventView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * Default constructor.
     *
     * @param context      instance of FragmentActivity
     * @param attrs
     * @param defStyleAttr
     */
    public CalendarEventView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.options, 0, 0);
        try {
            dateMode = a.getInteger(R.styleable.options_dateMode, 0);
            dateIntervalSize = a.getInteger(R.styleable.options_dateIntervalSize, 30);
        } finally {
            a.recycle();
        }

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.calendar_event_view, this);
    }

    /**
     * Invoke when the view has finished to load his own subviews.
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        viewPager = (ViewPager) findViewById(R.id.pager);
        titlePageIndicator = (TitlePageIndicator) findViewById(R.id.titles);
        buildCalendarEvent();
    }

    /**
     * Build the views for the calendar. Initialize ViewPager, FragmentPagerAdapter and TitlePagerIndicator.
     */
    private void buildCalendarEvent() {
        // Build viewpager
        screenSlidePagerAdapter = new ScreenSlidePagerAdapter(((FragmentActivity) getContext()).getSupportFragmentManager(), DateUtil.initDates(dateIntervalSize), dateMode);
        viewPager.setAdapter(screenSlidePagerAdapter);
        viewPager.setOnPageChangeListener(this);
        viewPager.setCurrentItem(dateIntervalSize / 2);

        //Bind the title indicator to the adapter
        titlePageIndicator.setViewPager(viewPager);
        titlePageIndicator.setOnPageChangeListener(this);
    }

    /**
     * Add events to the view of the selected date.
     *
     * @param events
     */
    public void addEventsToSelectedDate(List<? extends Event> events) {
        if (events != null || !events.isEmpty()) {
            // Add events to the fragment
            SlideDayFragment fragment = (SlideDayFragment) screenSlidePagerAdapter.getRegisteredFragment(viewPager.getCurrentItem());
            fragment.setEventList(events);
            // Save event in the FragmentPagerAdapter
            screenSlidePagerAdapter.saveEventsInMap(events, viewPager.getCurrentItem());
        }
    }

    /**
     * Get listener for the onDateSelected event.
     *
     * @return OnDateSelectedListener
     */
    public OnDateSelectedListener getOnDateSelectedListener() {
        return onDateSelectedListener;
    }

    /**
     * Set listener for the onDateSelected event.
     *
     * @param onDateSelectedListener
     */
    public void setOnDateSelectedListener(OnDateSelectedListener onDateSelectedListener) {
        this.onDateSelectedListener = onDateSelectedListener;
    }

    /**
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    /**
     * @param position
     */
    @Override
    public void onPageSelected(int position) {
    }

    /**
     * Invoked when state of one page changes. When scroll state is finished, add new list of dates if need it. Call onDateSelected of the listener.
     *
     * @param state
     */
    @Override
    public void onPageScrollStateChanged(int state) {
        // On finish the page scrolling.
        if (state == ViewPager.SCROLL_STATE_IDLE) {
            Date dateByPosition = screenSlidePagerAdapter.getDateByPosition(viewPager.getCurrentItem());
            // If first page, add new dates before the initial position.
            if (viewPager.getCurrentItem() == 0) {
                screenSlidePagerAdapter.addDates(0, DateUtil.previousDates(dateIntervalSize, dateByPosition));
                viewPager.setCurrentItem(dateIntervalSize, false);
            }
            // If last page, add new dates after the last position.
            else if (viewPager.getCurrentItem() == screenSlidePagerAdapter.getCount() - 1) {
                screenSlidePagerAdapter.addDates(DateUtil.nextDates(dateIntervalSize, dateByPosition));
            }
            // Call onDateSelected event and then, fill the date page with the events received.
            if (onDateSelectedListener != null) {
                List<? extends Event> events = onDateSelectedListener.onDateSelected(viewPager.getCurrentItem(), dateByPosition);
                addEventsToSelectedDate(events);
            }
        }
    }
}
