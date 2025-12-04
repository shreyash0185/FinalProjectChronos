package com.chronos.job_service.impl;


import com.chronos.job_service.dto.CreateScheduleRequest;
import com.chronos.job_service.dto.ScheduleResponse;
import com.chronos.job_service.entity.JobEntity;
import com.chronos.job_service.entity.JobScheduleEntity;
import com.chronos.job_service.mapper.ScheduleMapper;
import com.chronos.job_service.repository.JobRepository;
import com.chronos.job_service.repository.JobScheduleRepository;
import com.chronos.job_service.service.JobScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobScheduleServiceImpl implements JobScheduleService {

    private final JobScheduleRepository scheduleRepository;
    private final JobRepository jobRepository;
    private final ScheduleMapper mapper;

    @Override
    public ScheduleResponse createSchedule(CreateScheduleRequest request) {

        JobEntity job = jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new RuntimeException("Job not found"));

        JobScheduleEntity schedule = mapper.toEntity(request);
        schedule.setJob(job);

        schedule = scheduleRepository.save(schedule);
        return mapper.toResponse(schedule);
    }

    @Override
    public ScheduleResponse getSchedule(Long scheduleId) {
        JobScheduleEntity schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
        return mapper.toResponse(schedule);
    }

    @Override
    public List<ScheduleResponse> getSchedulesForJob(Long jobId) {
        return scheduleRepository.findAll().stream()
                .filter(s -> s.getJob().getJobId().equals(jobId))
                .map(mapper::toResponse)
                .toList();
    }
}