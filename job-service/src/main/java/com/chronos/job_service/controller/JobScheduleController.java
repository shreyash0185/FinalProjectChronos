package com.chronos.job_service.controller;

import com.chronos.job_service.dto.CreateScheduleRequest;
import com.chronos.job_service.dto.ScheduleResponse;
import com.chronos.job_service.service.JobScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class JobScheduleController {

    private final JobScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponse> createSchedule(@RequestBody CreateScheduleRequest request) {
        return ResponseEntity.ok(scheduleService.createSchedule(request));
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponse> getSchedule(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(scheduleService.getSchedule(scheduleId));
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<ScheduleResponse>> getSchedulesForJob(@PathVariable Long jobId) {
        return ResponseEntity.ok(scheduleService.getSchedulesForJob(jobId));
    }
}
