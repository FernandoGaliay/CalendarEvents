package calendar.caparso.es.calendar;

import java.util.Date;
import java.util.List;

import calendar.caparso.es.calendar.vo.Event;

/**
 * Created by Fernando Galiay on 31/05/2015.
 */
public interface OnDateSelectedListener {

   public List<? extends Event> onDateSelected(int position, Date pageDate);

}
