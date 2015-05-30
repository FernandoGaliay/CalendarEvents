package calendar.caparso.es.calendar.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import calendar.caparso.es.calendar.R;
import calendar.caparso.es.calendar.util.DateUtil;
import calendar.caparso.es.calendar.vo.EventVO;

/**
 * Created by Fernando Galiay on 30/05/2015.
 */
public class DayAdapter extends RecyclerView.Adapter<DayAdapter.ViewHolder> {

    private List<EventVO> eventVOList;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle, tvInitDate, tvEndDate;
        public ImageView ivCalendar;

        public ViewHolder(View v) {
            super(v);
        }
    }

    public DayAdapter(List<EventVO> eventVOList) {
        this.eventVOList = eventVOList;
    }

    @Override
    public DayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                    int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_event, parent, false);

        ViewHolder holder = new ViewHolder(v);
        holder.tvTitle = (TextView) v.findViewById(R.id.tv_title);
        holder.tvInitDate = (TextView) v.findViewById(R.id.tv_date_init);
        holder.tvEndDate = (TextView) v.findViewById(R.id.tv_date_end);
        holder.ivCalendar = (ImageView) v.findViewById(R.id.iv_calendar);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tvTitle.setText(getEventVOList().get(position).getTitle());
        holder.tvInitDate.setText(DateUtil.getHourFormat().format(getEventVOList().get(position).getInitDate()));
        holder.tvEndDate.setText(DateUtil.getHourFormat().format(getEventVOList().get(position).getEndDate()));

    }

    @Override
    public int getItemCount() {
        return eventVOList.size();
    }

    public List<EventVO> getEventVOList() {
        return eventVOList;
    }

    public void setEventVOList(List<EventVO> eventVOList) {
        this.eventVOList = eventVOList;
    }
}
