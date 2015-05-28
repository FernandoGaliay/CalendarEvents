package calendar.caparso.es.calendar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

    private List<Date> dateList;

    public ScreenSlidePagerAdapter(FragmentManager fm, List<Date> dateList) {
        super(fm);
        this.dateList = dateList;
    }

    @Override
    public Fragment getItem(int position) {
        return new ScreenSlidePageFragment().newInstance(dateList.get(position).getTime());
    }

    @Override
    public int getCount() {
        return dateList.size();
    }

    public void addDates(List<Date> dateList) {
        addDates(getCount(), dateList);
    }

    public void addDates(Integer position, List<Date> dateList) {
        this.dateList.addAll(position, dateList);
        notifyDataSetChanged();
    }

    public Date getDate(Integer position) {
        return dateList.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        return sf.format(dateList.get(position));
    }
}