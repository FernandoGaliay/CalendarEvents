package calendar.caparso.es.calendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import calendar.caparso.es.calendar.adapter.DayAdapter;
import calendar.caparso.es.calendar.vo.EventVO;

/**
 * Created by Fernando Galiay on 28/05/2015.
 */
public class ScreenSlidePageFragment extends Fragment {

    public static final String KEY_DATA = "KEY_MILISECONDS";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<EventVO> eventVOList;

    public static ScreenSlidePageFragment newInstance(List<EventVO> eventVOList) {
        ScreenSlidePageFragment f = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_DATA, new ArrayList<EventVO>(eventVOList));
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventVOList = (ArrayList<EventVO>) getArguments().getSerializable(KEY_DATA);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_pager, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_calendar);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new DayAdapter(eventVOList);
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }
}