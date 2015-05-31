package calendar.caparso.es.calendar.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import calendar.caparso.es.calendar.R;
import calendar.caparso.es.calendar.util.DateUtil;
import calendar.caparso.es.calendar.vo.Event;

/**
 * Created by Fernando Galiay on 30/05/2015.
 */
public class DayAdapter extends ArrayAdapter<Event> {

    private Context context;

    private List<? extends Event> events;

    private int layoutResourceId;

    public DayAdapter(Context context, int resource, List<Event> events) {
        super(context, resource, events);
        this.context = context;
        this.events = events;
        this.layoutResourceId = resource;
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            holder.tvEndDate = (TextView) convertView.findViewById(R.id.tv_end_date);
            holder.tvStartDate = (TextView) convertView.findViewById(R.id.tv_start_date);
            holder.ivCalendar = (ImageView) convertView.findViewById(R.id.iv_calendar);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvTitle.setText(events.get(position).getTitle());
        holder.tvStartDate.setText(DateUtil.getHourFormat().format(events.get(position).getStartDate()));
        holder.tvEndDate.setText(DateUtil.getHourFormat().format(events.get(position).getEndDate()));

        return convertView;
    }

    static class ViewHolder {
        TextView tvTitle;
        TextView tvEndDate;
        TextView tvStartDate;
        ImageView ivCalendar;
        int position;
    }

    public List<? extends Event> getEvents() {
        return events;
    }

    public void setEvents(List<? extends Event> events) {
        this.events = events;
        notifyDataSetChanged();
    }
}
