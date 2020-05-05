package com.gyj.gx.base.config.scheduling;

import com.gyj.gx.service.ContestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ScheduledTaskRunner implements ApplicationRunner {

    @Autowired
    private ContestService contestService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        contestService.addContestSchedules();
    }
}
