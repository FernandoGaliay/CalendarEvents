package calendar.caparso.es.calendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viewpagerindicator.TitlePageIndicator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import calendar.caparso.es.calendar.util.DateUtil;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    // Un mes por delante y por detras.
    private int DATE_INTERVAL_SIZE = 60;

    private int DAY_MODE = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_main, container, false);

        // Instantiate a ViewPager and a PagerAdapter.
        final ViewPager mPager = (ViewPager) rootView.findViewById(R.id.pager);
        final ScreenSlidePagerAdapter mPagerAdapter = new ScreenSlidePagerAdapter(getActivity().getSupportFragmentManager(), DateUtil.initDates(DATE_INTERVAL_SIZE), DAY_MODE);
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(DATE_INTERVAL_SIZE / 2);

        //Bind the title indicator to the adapter
        TitlePageIndicator titleIndicator = (TitlePageIndicator)rootView.findViewById(R.id.titles);
        titleIndicator.setViewPager(mPager);
        titleIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                // Primera posicion
                if (position == 0) {
                    mPagerAdapter.addDates(position, DateUtil.previousDates(DATE_INTERVAL_SIZE, mPagerAdapter.getDateByPosition(position)));
                    mPager.setCurrentItem(DATE_INTERVAL_SIZE, false);
                }
                // Ultima posicion
                else if (position == mPagerAdapter.getCount() - 1) {
                    mPagerAdapter.addDates(DateUtil.nextDates(DATE_INTERVAL_SIZE, mPagerAdapter.getDateByPosition(position)));
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        return rootView;
    }



}
