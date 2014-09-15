package ua.mind.mapreduce.server.service;

import java.util.Date;

/**
 * Created by Сергій on 15.09.2014.
 */
public interface MapreduceService {

    public float getSumByDate(Date fromDate, Date toDate);
    public float getSumByGroupId(long groupId);

}
