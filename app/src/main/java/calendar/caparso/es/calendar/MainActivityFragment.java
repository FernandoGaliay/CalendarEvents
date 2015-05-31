package calendar.caparso.es.calendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import calendar.caparso.es.calendar.vo.Event;
import calendar.caparso.es.calendar.vo.EventVO;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private int DATE_INTERVAL_SIZE = 60;

    private int DAY_MODE = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_main, container, false);

        final CalendarEventView calendarEventView = (CalendarEventView) rootView.findViewById(R.id.ce_day);
        calendarEventView.setOnDateSelectedListener(new OnDateSelectedListener() {
            @Override
            public List<? extends Event> onDateSelected(int position, Date pageDate) {
                return getEventList();
            }
        });

        TextView tvAddEvents = (TextView) rootView.findViewById(R.id.tv_add_events);
        tvAddEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              calendarEventView.addEventsToSelectedDate(getEventList());
            }
        });
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private List<EventVO> getEventList() {
        List<EventVO> eventVOList = new ArrayList<EventVO>();
        EventVO eventVO = new EventVO();
        eventVO.setTitle("Title event");
        Calendar calendar = Calendar.getInstance();
        eventVO.setStartDate(calendar.getTime());
        calendar.add(Calendar.DATE, 1);
        eventVO.setEndDate(calendar.getTime());
        eventVOList.add(eventVO);
        return eventVOList;
    }

}
