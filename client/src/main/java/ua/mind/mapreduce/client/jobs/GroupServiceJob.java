package ua.mind.mapreduce.client.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.mind.mapreduce.client.dto.ResponceDTO;
import ua.mind.mapreduce.client.service.RetrieveService;

import javax.ws.rs.core.MediaType;

/**
 * Created by mind on 16.09.14.
 * Thread job(thread) that invoke one call to rest for getting sum by groupid
 */
public class GroupServiceJob extends ServiceJob {
    private static final Logger LOGGER = LoggerFactory.getLogger(RetrieveService.class);
    private final String REQUEST_PATH = "/groupidsum";
    private Long groupId;

    public GroupServiceJob(String host, Long groupId) {
        super(host);
        this.groupId = groupId;
    }

    @Override
    public Double call() throws Exception {
        ResponceDTO dto = service.path(REST_PATH).path(REQUEST_PATH)
                .queryParam("groupId", String.valueOf(groupId)).accept(MediaType.APPLICATION_JSON).get(ResponceDTO.class);
        LOGGER.info("Response from " + service.getURI() + ": " + dto);
        return dto.getSum();
    }
}
