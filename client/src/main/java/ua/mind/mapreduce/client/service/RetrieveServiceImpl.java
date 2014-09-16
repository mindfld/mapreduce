package ua.mind.mapreduce.client.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.mind.mapreduce.client.jobs.DateServiceJob;
import ua.mind.mapreduce.client.jobs.GroupServiceJob;
import ua.mind.mapreduce.client.jobs.ServiceJob;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by mind on 16.09.14.
 */
public class RetrieveServiceImpl implements RetrieveService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RetrieveService.class);
    private static final String GROUIDS_FILE = "groupids.txt";
    private static final String HOSTS_FILE = "hosts";

    List<Long> groupIdList;
    List<String> hosts;

    public RetrieveServiceImpl() {
        this.groupIdList = populateGroupIds();
        this.hosts = populateHosts();
    }


    @Override
    public float getSumByDate(Date fromDate, Date toDate) {
        if (fromDate==null || toDate==null){
            return 0;
        }
        if (fromDate.compareTo(toDate)>0){
            return 0;
        }

        float sum = 0;
        ExecutorService executor = Executors.newFixedThreadPool(hosts.size());
        List<ServiceJob> jobs = new ArrayList<>();
        for (int i = 0; i < hosts.size(); i++) {
            jobs.add(new DateServiceJob(hosts.get(i), fromDate, toDate));
        }

        List<Future<Double>> results = null;
        try {
            results = executor.invokeAll(jobs);
            executor.shutdown();
            for (Future<Double> item : results) {
                sum += item.get();
            }
        } catch (InterruptedException e) {
            LOGGER.error("Jobs was interrupted" + e);
        } catch (ExecutionException e) {
            LOGGER.error("Jobs unable to be executed" + e);
        }
        return sum;
    }

    @Override
    public float getSumByGroupId(long groupId) {
        float sum = 0;
        ExecutorService executor = Executors.newFixedThreadPool(hosts.size());
        List<ServiceJob> jobs = new ArrayList<>();
        for (int i = 0; i < hosts.size(); i++) {
            jobs.add(new GroupServiceJob(hosts.get(i), groupId));
        }

        List<Future<Double>> results = null;
        try {
            results = executor.invokeAll(jobs);
            executor.shutdown();
            for (Future<Double> item : results) {
                sum += item.get();
            }
        } catch (InterruptedException e) {
            LOGGER.error("Jobs was interrupted" + e);
        } catch (ExecutionException e) {
            LOGGER.error("Jobs unable to be executed" + e);
        }
        return sum;
    }

    private List<Long> populateGroupIds() {
        List<String> numbers = getLinesFromFile(GROUIDS_FILE);
        List<Long> list = new ArrayList<>(numbers.size());
        for (String num : numbers) {
            list.add(Long.parseLong(num));
        }
        return list;
    }

    private List<String> populateHosts() {
        return getLinesFromFile(HOSTS_FILE);
    }

    private List<String> getLinesFromFile(String filename) {
        List<String> lines = new ArrayList<>();
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
        if (stream != null) {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
                String line;
                while ((line = br.readLine()) != null) {
                    lines.add(line);
                }
                br.close();
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("Unable to open file");
            } catch (IOException e) {
                LOGGER.error("Unable to read lines from file:" + filename);
            } finally {
            }
        } else {
            LOGGER.error("Unable to get file as stream");
            return null;
        }
        return lines;
    }
    public long getRandomGroupId(){
        Random rnd = new Random();
        if (groupIdList!=null && !groupIdList.isEmpty()) {
            return groupIdList.get(rnd.nextInt(groupIdList.size() - 1));
        }else{
            return 0;
        }
    }
}
