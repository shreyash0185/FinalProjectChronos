package com.chronos.executor_service.controller;

import com.chronos.executor_service.entity.ExecutedJobEntity;
import com.chronos.executor_service.repository.ExecutedJobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/executor")
@RequiredArgsConstructor
public class ExecutorController {

    private final ExecutedJobRepository repository;

    @GetMapping("/jobs")
    public ResponseEntity<List<ExecutedJobEntity>> listJobs() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<ExecutedJobEntity> getJob(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/jobs/by-jobid/{jobId}")
    public ResponseEntity<ExecutedJobEntity> getJobByJobId(@PathVariable String jobId) {
        return repository.findByJobId(jobId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
