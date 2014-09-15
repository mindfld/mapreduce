package ua.mind.mapreduce.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.mind.mapreduce.server.dto.ResponceDTO;
import ua.mind.mapreduce.server.service.MapreduceService;

import java.util.Date;

@Controller
@RequestMapping("/")
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
    ResponceDTO sumByDate(@RequestParam(value = "fromDate") @DateTimeFormat(pattern = "dd/MM/yyyy") Date fromDate,
                          @RequestParam(value = "toDate") @DateTimeFormat(pattern = "dd/MM/yyyy") Date toDate) {
        LOGGER.debug("GetSumByDate started for period " + fromDate + " - " + toDate);

        long benchmarkTime = System.nanoTime();
        ResponceDTO dto = new ResponceDTO(service.getSumByDate(fromDate, toDate), fromDate, toDate, -1);
        benchmarkTime = System.nanoTime() - benchmarkTime;

        LOGGER.info("Object find executed in: " + benchmarkTime);
        dto.setSearchtime(benchmarkTime);

        return dto;
    }

    @RequestMapping("/groupidsum")
    public
    @ResponseBody
    ResponceDTO sumByGroupId(@RequestParam(value = "groupId") long groupId) {
        LOGGER.debug("GetSumByGroupId started:" + groupId);

        long benchmarkTime = System.nanoTime();
        ResponceDTO dto = new ResponceDTO(service.getSumByGroupId(groupId), null, null, groupId);
        benchmarkTime = System.nanoTime() - benchmarkTime;

        LOGGER.info("Object find executed in: " + benchmarkTime);
        dto.setSearchtime(benchmarkTime);

        return dto;
    }

}

