package com.example.schedules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FixedRateSchedulerDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(FixedRateSchedulerDemo.class);


    //This fixed rate starts the task every N milliseconds
    //ATTENTION: it does not wait for the previous task end
    @Scheduled(fixedRate = 5000)
    public void showTimeWithFixedRateScheduler() {
        LOGGER.info("Hello, from fixed rate scheduler at {}", LocalDateTime.now());
    }
}
