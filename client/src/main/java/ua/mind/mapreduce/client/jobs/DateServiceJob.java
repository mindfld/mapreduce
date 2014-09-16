package ua.mind.mapreduce.client.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.mind.mapreduce.client.dto.ResponceDTO;
import ua.mind.mapreduce.client.service.RetrieveService;

import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mind on 16.09.14.
 * Thread job(thread) that invoke one call to rest for getting sum by date
 */
public class DateServiceJob extends ServiceJob {
    private static final Logger LOGGER = LoggerFactory.getLogger(RetrieveService.class);
    private final String REQUEST_PATH = "/datesum";
    private Date fromDate;
    private Date toDate;

    public DateServiceJob(String host, Date fromDate, Date toDate) {
        super(host);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public Double call() throws Exception {
        ResponceDTO dto = service.path(REST_PATH).path(REQUEST_PATH)
                .queryParam("fromDate", new SimpleDateFormat("dd/MM/yyyy").format(fromDate))
                .queryParam("toDate", new SimpleDateFormat("dd/MM/yyyy").format(toDate)).accept(MediaType.APPLICATION_JSON).get(ResponceDTO.class);
        LOGGER.info("Responce from " + service.getURI() + ": " + dto);
        return dto.getSum();
    }
}
