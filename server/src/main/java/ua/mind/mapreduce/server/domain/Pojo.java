package ua.mind.mapreduce.server.domain;

import java.util.Date;

/**
 * @author mind
 * Simple Pojo.
 */
public class Pojo implements Comparable<Pojo>{

    private long id;
    private long groupId;
    private double money;
    private Date date;

    public Pojo(long id, long groupId, float money, Date date) {
        this.id = id;
        this.groupId = groupId;
        this.money = money;
        this.date = date;
    }

    public Pojo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }



    public void setMoney(float money) {
        this.money = money;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pojo pojo = (Pojo) o;

        if (groupId != pojo.groupId) return false;
        if (id != pojo.id) return false;
        if (Double.compare(pojo.money, money) != 0) return false;
        if (!date.equals(pojo.date)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (groupId ^ (groupId >>> 32));
        temp = Double.doubleToLongBits(money);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + date.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Pojo{" +
                "id=" + id +
                ", groupId=" + groupId +
                ", money=" + money +
                ", date=" + date +
                '}';
    }

    @Override
    public int compareTo(Pojo o) {
        return date.compareTo(o.getDate());
    }
}