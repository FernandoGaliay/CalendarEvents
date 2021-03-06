package calendar.caparso.es.calendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import calendar.caparso.es.calendar.adapter.DayAdapter;
import calendar.caparso.es.calendar.vo.Event;

/**
 * Created by Fernando Galiay on 28/05/2015.
 */
public class SlideDayFragment extends Fragment {

    private static String EVENTS_DATA = "EVENTS_DATA";

    /**
     * List of the events that are going to shown in the list.
     */
    private List<? extends Event> eventList;

    /**
     * List adapter with day design.
     */
    private DayAdapter dayAdapter;

    /**
     * Default constructor
     *
     * @param events List of the events that are going to shown in the list.
     * @return SlideDayFragment
     */
    public static SlideDayFragment newInstance(List<? extends Event> events) {
        SlideDayFragment f = new SlideDayFragment();
        Bundle args = new Bundle();
        args.putSerializable(EVENTS_DATA, new ArrayList<Event>(events));
        f.setArguments(args);
        return f;
    }

    /**
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null && args.containsKey(EVENTS_DATA)) {
            eventList = (List<? extends Event>) args.getSerializable(EVENTS_DATA);
        }
    }

    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_pager, container, false);

        ListView lvCalendar = (ListView) rootView.findViewById(R.id.lv_calendar);
        TextView tvEmpty = (TextView) rootView.findViewById(R.id.tv_empty);
        lvCalendar.setEmptyView(tvEmpty);
        dayAdapter = new DayAdapter(getActivity(), R.layout.row_event, (List<Event>) eventList);
        lvCalendar.setAdapter(dayAdapter);

        return rootView;
    }

    /**
     * Refresh the list of the events.
     *
     * @param eventList
     */
    public void setEventList(List<? extends Event> eventList) {
        this.eventList = eventList;
        if (dayAdapter != null)
            dayAdapter.setEvents(eventList);
    }
}