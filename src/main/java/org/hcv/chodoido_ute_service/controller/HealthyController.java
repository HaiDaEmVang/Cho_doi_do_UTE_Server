package org.hcv.chodoido_ute_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class HealthyController {
    @GetMapping("/api/healthy")
    public void call(){
        WebClient.builder().build().get().uri("https://call-render-get-up.onrender.com/api/ok")
                .retrieve()
                .bodyToMono(String.class)
                .block();;
    }
}
