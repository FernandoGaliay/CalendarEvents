package calendar.caparso.es.calendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import calendar.caparso.es.calendar.adapter.DayAdapter;
import calendar.caparso.es.calendar.vo.Event;

/**
 * Created by Fernando Galiay on 28/05/2015.
 */
public class SlideDayFragment extends Fragment {

    private List<? extends Event> eventList;

    private DayAdapter dayAdapter;

    public static SlideDayFragment newInstance() {
        SlideDayFragment f = new SlideDayFragment();
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_pager, container, false);

        ListView lvCalendar = (ListView) rootView.findViewById(R.id.lv_calendar);
        dayAdapter = new DayAdapter(getActivity(), R.layout.row_event, new ArrayList<Event>());
        lvCalendar.setAdapter(dayAdapter);

        return rootView;
    }

    public void setEventList(List<? extends Event> eventList) {
        if (dayAdapter != null)
            dayAdapter.setEvents(eventList);
    }
}