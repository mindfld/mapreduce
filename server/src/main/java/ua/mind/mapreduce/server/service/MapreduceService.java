package ua.mind.mapreduce.server.service;

import java.util.Date;

/**
 * Created by Сергій on 15.09.2014.
 * Interface of general service that retrieves and returns sum of money to rest.
 * @see ua.mind.mapreduce.server.service.impl.MapreduceServiceImpl
 * @see ua.mind.mapreduce.server.service.impl.UnsortedMapreduceServiceImpl
 */
public interface MapreduceService {

    public float getSumByDate(Date fromDate, Date toDate);
    public float getSumByGroupId(long groupId);

}
