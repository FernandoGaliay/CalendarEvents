package calendar.caparso.es.calendar.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Fernando Galiay on 30/05/2015.
 */
public class EventVO extends Event implements Serializable {

    private String titulo;

    private Date start;

    private Date end;

    @Override
    public void setTitle(String title) {
        this.titulo = title;
    }

    @Override
    public String getTitle() {
        return titulo;
    }

    @Override
    public void setStartDate(Date startDate) {
        this.start = startDate;
    }

    @Override
    public Date getStartDate() {
        return start;
    }

    @Override
    public void setEndDate(Date endDate) {
        this.end = endDate;
    }

    @Override
    public Date getEndDate() {
        return end;
    }
}
