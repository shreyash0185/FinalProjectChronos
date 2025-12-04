package com.chronos.job_service.service;

import com.chronos.job_service.dto.CreateScheduleRequest;
import com.chronos.job_service.dto.ScheduleResponse;

import java.util.List;

public interface JobScheduleService {

    ScheduleResponse createSchedule(CreateScheduleRequest request);

    ScheduleResponse getSchedule(Long scheduleId);

    List<ScheduleResponse> getSchedulesForJob(Long jobId);
}