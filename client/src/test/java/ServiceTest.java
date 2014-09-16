import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.mind.mapreduce.client.service.RetrieveService;
import ua.mind.mapreduce.client.service.RetrieveServiceImpl;

import java.util.Date;

import static org.junit.Assert.assertNotEquals;

/**
 * Created by mind on 16.09.14.
 */
public class ServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceTest.class);

    @Test
    public void invocationOfSumByDateShouldReturnNotEmptySum() {
        RetrieveService service = new RetrieveServiceImpl();
        double sum = service.getSumByDate(new Date(626047200000L), new Date(1290117600000L));
        assertNotEquals(0, sum, 0);
        LOGGER.info("Sum returned: " + sum);
    }

    /*Posibly sometimes fails. When there no elements with provided groupid*/
    @Test
    public void invocationOfSumByGroupShouldReturnNotEmptySum() {

        RetrieveService service = new RetrieveServiceImpl();
        double sum = service.getSumByGroupId(((RetrieveServiceImpl) service).getRandomGroupId());
        assertNotEquals(0, sum, 0);
        LOGGER.info("Sum returned: " + sum);
    }

    /*Trying to simulate 100 users that need sum by groupid*/
    @Test
    public void invocationOfSumByGroupWith1000Hamsters() {
        for (int i = 0; i < 100; i++) {
            (new Thread(new TestHamster())).start();
            LOGGER.info("Thread started #"+i);
        }
    }

    private class TestHamster implements Runnable {
        public void run() {
            RetrieveService service = new RetrieveServiceImpl();
            double sum = service.getSumByGroupId(((RetrieveServiceImpl) service).getRandomGroupId());
            assertNotEquals(0, sum, 0);
            LOGGER.info("Sum returned: " + sum);
        }
    }
}
