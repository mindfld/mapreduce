package ua.mind.mapreduce.client.dto;

import java.util.Date;

/**
 * Created by Сергій on 15.09.2014.
 * Class for transfer sum from one rest
 */
public class ResponceDTO {
    private double sum;
    private Date fromDate;
    private Date toDate;
    private long groupId;
    private long searchtime;

    public ResponceDTO() {
    }

    public ResponceDTO(double sum, Date fromDate, Date toDate, long groupId) {
        this.sum = sum;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.groupId = groupId;
    }


    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Long  fromDate) {
        if (fromDate!=null) this.fromDate = new Date(fromDate);
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Long toDate) {
        if (toDate!=null) this.toDate = new Date(toDate);
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public long getSearchtime() {
        return searchtime;
    }

    public void setSearchtime(long searchtime) {
        this.searchtime = searchtime;
    }

    @Override
    public String toString() {
        return "ResponceDTO{" +
                "sum=" + sum +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", groupId=" + groupId +
                ", searchtime=" + searchtime +
                '}';
    }
}
