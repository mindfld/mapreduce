package ua.mind.mapreduce.client.service;

import java.util.Date;

/**
 * Created by mind on 16.09.14.
 */
public interface RetrieveService {

    public float getSumByDate(Date fromDate, Date toDate);

    public float getSumByGroupId(long groupId);
}
