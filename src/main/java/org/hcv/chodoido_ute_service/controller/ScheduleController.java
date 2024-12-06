package org.hcv.chodoido_ute_service.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ScheduleController {
    @Scheduled(fixedRate = 1000*60)
    public void schedule(){
        log.info("server_healthy", "Server is live");
    }
}
