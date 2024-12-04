package org.hcv.chodoido_ute_service.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Scheduled(fixedRate = 300000)
    @GetMapping("/heath")
    public void schedule(){
    }
}
