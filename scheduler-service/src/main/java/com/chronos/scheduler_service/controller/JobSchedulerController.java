package com.chronos.scheduler_service.controller;

import com.chronos.scheduler_service.dto.CreateJobRequest;
import com.chronos.scheduler_service.dto.UpdateCronRequest;
import com.chronos.scheduler_service.service.SchedulerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scheduler")
@RequiredArgsConstructor
public class JobSchedulerController {

    private final SchedulerService schedulerService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateJobRequest request) {
        return ResponseEntity.ok(schedulerService.createJob(request));
    }

    @PutMapping("/update-cron")
    public ResponseEntity<?> updateCron(@RequestBody UpdateCronRequest request) {
        return ResponseEntity.ok(schedulerService.updateCron(request));
    }

    @PostMapping("/pause/{jobId}")
    public ResponseEntity<?> pause(@PathVariable String jobId) throws Exception {
        schedulerService.pauseJob(jobId);
        return ResponseEntity.ok("Job paused");
    }

    @PostMapping("/resume/{jobId}")
    public ResponseEntity<?> resume(@PathVariable String jobId) throws Exception {
        schedulerService.resumeJob(jobId);
        return ResponseEntity.ok("Job resumed");
    }

    @DeleteMapping("/delete/{jobId}")
    public ResponseEntity<?> delete(@PathVariable String jobId) throws Exception {
        schedulerService.deleteJob(jobId);
        return ResponseEntity.ok("Job deleted");
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(schedulerService.getAllJobs());
    }
}
