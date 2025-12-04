package com.chronos.scheduler_service.service;

import com.chronos.scheduler_service.dto.CreateJobRequest;
import com.chronos.scheduler_service.dto.UpdateCronRequest;
import com.chronos.scheduler_service.entity.ScheduledJobEntity;
import com.chronos.scheduler_service.repository.ScheduledJobRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class SchedulerService {

    private final SchedulerFactoryBean schedulerFactoryBean;
    private final ScheduledJobRepository repo;
    private final QuartzJobService quartzJobService;

    public String createJob(CreateJobRequest request) {
        String jobId = UUID.randomUUID().toString();

        quartzJobService.registerJob(jobId, request.getCronExpression(), request.getPayload());

        repo.save(new ScheduledJobEntity(jobId, request.getCronExpression(), request.getPayload()));

        return jobId;
    }

    public String updateCron(UpdateCronRequest request) {
        quartzJobService.updateTrigger(request.getJobId(), request.getCronExpression());

        ScheduledJobEntity entity = repo.findById(request.getJobId()).get();
        entity.setCronExpression(request.getCronExpression());
        repo.save(entity);

        return "Cron updated";
    }

    public void pauseJob(String jobId) throws Exception {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduler.pauseJob(JobKey.jobKey(jobId));
    }

    public void resumeJob(String jobId) throws Exception {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduler.resumeJob(JobKey.jobKey(jobId));
    }

    public void deleteJob(String jobId) throws Exception {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduler.deleteJob(JobKey.jobKey(jobId));
        repo.deleteById(jobId);
    }

    public List<ScheduledJobEntity> getAllJobs() {
        return repo.findAll();
    }
}
