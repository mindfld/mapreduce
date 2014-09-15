package ua.mind.mapreduce.server.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by mind on 15.09.14.
 */
public class UnsortedStorage {
    private static final Logger LOGGER = LoggerFactory.getLogger(UnsortedStorage.class);

    public static final int MAX_ITEMS = 100000;
    private List<Pojo> pojoList = new LinkedList<>();


    public void fillStorage() {
        Random rnd = new Random();
        long groupId = rnd.nextInt(MAX_ITEMS);
        for (int i = 0; i < MAX_ITEMS; i++) {
            float money = rnd.nextFloat();
            Date date = getRandomDate();
            if (i % 5 == 0) {
                groupId = rnd.nextInt(MAX_ITEMS);
            }
            Pojo pojo= new Pojo(i, groupId, money,date);
            pojoList.add(pojo);

        }
    }

    private void sortByDate(List<Pojo> pojoList) {
        Collections.sort(pojoList, new Comparator<Pojo>() {
            @Override
            public int compare(Pojo o1, Pojo o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
    }

    private Date getRandomDate() {
        long offset = Timestamp.valueOf("1980-01-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2015-01-01 00:00:00").getTime();
        long diff = end - offset + 1;
        Date rand = new Date(offset + (long) (Math.random() * diff));
        return rand;
    }

    public List<Pojo> getPojoList() {
        return pojoList;
    }

    public void setPojoList(List<Pojo> pojoList) {
        this.pojoList = pojoList;
    }

}
