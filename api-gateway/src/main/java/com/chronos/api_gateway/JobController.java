package com.chronos.api_gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {
    @GetMapping("/api/jobs/health")
    public String health() {
        return "api-gateway: OK";
    }
}
