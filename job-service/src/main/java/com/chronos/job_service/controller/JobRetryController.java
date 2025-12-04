package com.chronos.job_service.controller;

import com.chronos.job_service.dto.RetryResponse;
import com.chronos.job_service.service.JobRetryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/retries")
@RequiredArgsConstructor
public class JobRetryController {

    private final JobRetryService retryService;

    @GetMapping("/execution/{executionId}")
    public ResponseEntity<List<RetryResponse>> getRetries(@PathVariable Long executionId) {
        return ResponseEntity.ok(retryService.getRetriesForExecution(executionId));
    }
}
