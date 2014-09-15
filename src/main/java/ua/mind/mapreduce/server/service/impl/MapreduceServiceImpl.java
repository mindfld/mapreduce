package ua.mind.mapreduce.server.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.mind.mapreduce.server.domain.Pojo;
import ua.mind.mapreduce.server.domain.SortedStorage;
import ua.mind.mapreduce.server.service.MapreduceService;

import java.util.*;

/**
 * Created by Сергій on 15.09.2014.
 */
@Service
public class MapreduceServiceImpl implements MapreduceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapreduceServiceImpl.class);

    @Autowired
    SortedStorage storage;


    @Override
    public float getSumByDate(Date fromDate, Date toDate) {
        float sum=0;
        List<Pojo>list = storage.getPojoList();
        ListIterator <Pojo> iterator= list.listIterator();
        while (iterator.hasNext()){
            Pojo next =iterator.next();

            if (next.getDate().compareTo(fromDate)>=0){
                sum+=next.getMoney();
                while (iterator.hasNext()){
                    next=iterator.next();
                    if(next.getDate().compareTo(toDate)>=0){
                        break;
                    }else{
                        sum+=next.getMoney();
                    }
                }
                break;
            }
        }
        return sum;
    }

    @Override
    public float getSumByGroupId(long groupId) {
        List<Pojo> list = storage.getPojoMap().get(groupId);
        float sum =0;
        for (int i = 0; i < list.size(); i++) {
            sum+=list.get(i).getMoney();
        }
        return sum;
    }
}
