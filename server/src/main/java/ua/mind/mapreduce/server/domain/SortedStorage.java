package ua.mind.mapreduce.server.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by mind on 15.09.14.
 * Other implementation of storage for all Pojos.
 * Links to pojos stored in two collections pojoList for retrieving items by date
 * (it's LinkedList, because we assume that values might be inserted in the middle of it).
 * Mind that it is  sorted for faster access to elements, possibly search of elements should be changed to binary.
 *
 * pojoMap consists of links to all pojos grouped by groupId. That really speed up retrieving of elements.
 *
 * Mb all that staff doesn't needed, but i did it for faster access.
 * @see ua.mind.mapreduce.server.domain.UnsortedStorage
 * @see INFO_BY_DATE.txt
 * @see INFO_BY_GROUP.txt
 */
public class SortedStorage {
    private static final Logger LOGGER = LoggerFactory.getLogger(SortedStorage.class);

    public static final int MAX_ITEMS = 1000000;
    public static final String GROUIDS_FILE = "grouids.txt";
    private List<Pojo> pojoList = new LinkedList<>();
    private Map<Long, List<Pojo>> pojoMap = new HashMap<>();

    /**
     * Not really correct place for this logic, but I assume this code will not be needed in real system, so i just left it here
     * */
    public void fillStorage() {
        //groupid get from file in order to pojos with same groupid on both jvms
        List<Long> groupIdList = populateGroupIds();
        Random rnd = new Random();

        for (int i = 0; i < MAX_ITEMS; i++) {
            float money = rnd.nextFloat();
            Date date = getRandomDate();

            long groupId = 0;
            if (groupIdList != null) {
                groupId = groupIdList.get(rnd.nextInt(groupIdList.size() - 1));
            } else {
                groupId = rnd.nextInt(MAX_ITEMS);
            }

            Pojo pojo = new Pojo(i, groupId, money, date);
            pojoList.add(pojo);

            List<Pojo> mapList = pojoMap.get(groupId);
            if (mapList == null) {
                List<Pojo> l = new ArrayList<>();
                l.add(pojo);
                pojoMap.put(groupId, l);
            } else {
                mapList.add(pojo);
            }
        }
        long benchmarkTime = System.nanoTime();
        //sorting for faster retrieving and access to elements
        sortByDate(pojoList);
        benchmarkTime = System.nanoTime() - benchmarkTime;
        LOGGER.info("List sorted in: " + benchmarkTime);
    }

    private List<Long> populateGroupIds() {
        List<Long> list = new ArrayList<>();
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(GROUIDS_FILE);
        if (stream != null) {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
                String line;
                while ((line = br.readLine()) != null) {
                    list.add(Long.parseLong(line));
                }
                br.close();
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("Unable to open file");
            } catch (IOException e) {
                LOGGER.error("Unable to read groupIds from file");
            } finally {
            }
        } else {
            LOGGER.error("Unable to get file as stream");
            return null;
        }

        return list;
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

    public Map<Long, List<Pojo>> getPojoMap() {
        return pojoMap;
    }

    public void setPojoMap(Map<Long, List<Pojo>> pojoMap) {
        this.pojoMap = pojoMap;
    }
}
