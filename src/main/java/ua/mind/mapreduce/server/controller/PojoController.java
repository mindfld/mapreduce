package ua.mind.mapreduce.server.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class PojoController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/datesum")
    public Long datesum (@RequestParam(value="fromDate")     @DateTimeFormat(pattern = "dd/MM/yyyy") Date fromDate,
                         @RequestParam(value="toDate")     @DateTimeFormat(pattern = "dd/MM/yyyy") Date toDate) {
        System.out.println(fromDate);
        System.out.println(toDate);
        return new Long(1);
    }
}
