package ua.mind.mapreduce.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.mind.mapreduce.server.dto.ResponseDTO;
import ua.mind.mapreduce.server.dto.helpers.DateSerializer;
import ua.mind.mapreduce.server.service.MapreduceService;

import java.util.Date;

/**
 * REST Controller that respond with some of money by date or groupid
 */
@Controller
@RequestMapping("/rest")
public class JSONController {
    private static final Logger LOGGER = LoggerFactory.getLogger(JSONController.class);

    private MapreduceService service;

    @Autowired
    public JSONController(MapreduceService service) {
        this.service = service;
    }

    @RequestMapping("/datesum")
    public
    @ResponseBody
    ResponseDTO sumByDate(@RequestParam(value = "fromDate") @DateTimeFormat(pattern = DateSerializer.DATE_FORMAT) Date fromDate,
                          @RequestParam(value = "toDate") @DateTimeFormat(pattern = DateSerializer.DATE_FORMAT) Date toDate) {
        LOGGER.debug("GetSumByDate started for period " + fromDate + " - " + toDate);

        //see logs for service invocation time. Different for both implementations
        long benchmarkTime = System.nanoTime();
        ResponseDTO dto = new ResponseDTO(service.getSumByDate(fromDate, toDate), fromDate, toDate, -1);
        benchmarkTime = System.nanoTime() - benchmarkTime;

        LOGGER.info("Object find executed in: " + benchmarkTime);
        dto.setSearchtime(benchmarkTime);

        return dto;
    }

    @RequestMapping("/groupidsum")
    public
    @ResponseBody
    ResponseDTO sumByGroupId(@RequestParam(value = "groupId") long groupId) {
        LOGGER.debug("GetSumByGroupId started:" + groupId);

        //see logs for service invocation time. Different for both implementations
        long benchmarkTime = System.nanoTime();
        ResponseDTO dto = new ResponseDTO(service.getSumByGroupId(groupId), null, null, groupId);
        benchmarkTime = System.nanoTime() - benchmarkTime;

        LOGGER.info("Object find executed in: " + benchmarkTime);
        dto.setSearchtime(benchmarkTime);

        return dto;
    }

}

