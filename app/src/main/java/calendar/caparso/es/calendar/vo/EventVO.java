package calendar.caparso.es.calendar.vo;

import java.util.Date;

/**
 * Created by Fernando Galiay on 30/05/2015.
 */
public class EventVO {

    private String title;

    private Date initDate;

    private Date endDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getInitDate() {
        return initDate;
    }

    public void setInitDate(Date initDate) {
        this.initDate = initDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
