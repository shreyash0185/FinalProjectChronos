package com.chronos.job_service.controller;

import com.chronos.job_service.dto.ExecutionResponse;
import com.chronos.job_service.service.JobExecutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/executions")
@RequiredArgsConstructor
public class JobExecutionController {

    private final JobExecutionService executionService;

    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<ExecutionResponse>> getExecutionsForJob(@PathVariable Long jobId) {
        return ResponseEntity.ok(executionService.getExecutionsForJob(jobId));
    }

    @GetMapping("/{executionId}")
    public ResponseEntity<ExecutionResponse> getExecution(@PathVariable Long executionId) {
        return ResponseEntity.ok(executionService.getExecution(executionId));
    }
}
