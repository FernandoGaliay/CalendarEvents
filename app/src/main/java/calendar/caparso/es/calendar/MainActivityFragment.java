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

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    // Un mes por delante y por detras.
    private Integer DATE_INTERVAL_SIZE = 60;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_main, container, false);
        // Instantiate a ViewPager and a PagerAdapter.
        final ViewPager mPager = (ViewPager) rootView.findViewById(R.id.pager);
        final ScreenSlidePagerAdapter mPagerAdapter = new ScreenSlidePagerAdapter(getActivity().getSupportFragmentManager(), initDate());
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
                    mPagerAdapter.addDates(position, previousDates(mPagerAdapter.getDate(position)));
                    mPager.setCurrentItem(DATE_INTERVAL_SIZE, false);
                }
                // Ultima posicion
                else if(position == mPagerAdapter.getCount() - 1){
                    mPagerAdapter.addDates(nextDates(mPagerAdapter.getDate(position)));
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        return rootView;
    }

    public List<Date> initDate(){
        List<Date> dateList = new ArrayList<Date>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -((DATE_INTERVAL_SIZE / 2) + 1));
        for(int i = 0; i <= DATE_INTERVAL_SIZE; i++){
            calendar.add(Calendar.DATE, 1);
            dateList.add(calendar.getTime());
        }
        return dateList;
    }

    public List<Date> previousDates(Date firstDate){
        List<Date> dateList = new ArrayList<Date>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(firstDate);
        for(int i = DATE_INTERVAL_SIZE; i > 0; i--){
            calendar.setTime(firstDate);
            calendar.add(Calendar.DATE, -i);
            dateList.add(calendar.getTime());
        }
        return dateList;
    }

    public List<Date> nextDates(Date lastDate){
        List<Date> dateList = new ArrayList<Date>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lastDate);
        for(int i = 1; i <= DATE_INTERVAL_SIZE; i++){
            calendar.add(Calendar.DATE, 1);
            dateList.add(calendar.getTime());
        }
        return dateList;
    }

}
