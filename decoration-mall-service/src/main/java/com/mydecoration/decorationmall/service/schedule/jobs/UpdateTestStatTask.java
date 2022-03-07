package com.mydecoration.decorationmall.service.schedule.jobs;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class UpdateTestStatTask implements Runnable{

    @Override
    public void run() {
        try {

        } catch (Exception e) {
            log.error("UpdateTestStatTask run failed:{}", e.getMessage());
        }
    }

}
