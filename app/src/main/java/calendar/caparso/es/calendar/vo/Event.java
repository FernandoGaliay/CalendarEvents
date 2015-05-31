package calendar.caparso.es.calendar.vo;

import java.util.Date;

/**
 * Created by Fernando Galiay on 30/05/2015.
 */
public abstract class Event {

    abstract public void setTitle(String title);

    abstract public String getTitle();

    abstract public void setStartDate(Date startDate);

    abstract public Date getStartDate();

    abstract public void setEndDate(Date endDate);

    abstract public Date getEndDate();
}
