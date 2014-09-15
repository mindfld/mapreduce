package ua.mind.mapreduce.server.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.mind.mapreduce.server.domain.Pojo;
import ua.mind.mapreduce.server.domain.SortedStorage;
import ua.mind.mapreduce.server.domain.UnsortedStorage;
import ua.mind.mapreduce.server.service.MapreduceService;

import java.util.Date;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Сергій on 15.09.2014.
 */
//@Service
public class UnsortedMapreduceServiceImpl implements MapreduceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UnsortedMapreduceServiceImpl.class);

    @Autowired
    UnsortedStorage storage;


    @Override
    public float getSumByDate(Date fromDate, Date toDate) {
        List<Pojo> list = storage.getPojoList();
        float sum=0;
        for (int i = 0; i < list.size(); i++) {
            Pojo curr = list.get(i);
            if (curr.getDate().compareTo(fromDate)>=0 && curr.getDate().compareTo(toDate)<=0){
                sum+=curr.getMoney();
            }
        }
        return sum;
    }

    @Override
    public float getSumByGroupId(long groupId) {
        List<Pojo> list = storage.getPojoList();
        float sum=0;
        for (int i = 0; i < list.size(); i++) {
            Pojo curr = list.get(i);
            if (curr.getGroupId()==groupId){
                sum+=curr.getMoney();
            }
        }
        return sum;
    }
}
