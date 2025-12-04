package com.chronos.executor_service.service.Impl;

import com.chronos.executor_service.dto.JobRequest;
import com.chronos.executor_service.dto.JobStatusResponse;
import com.chronos.executor_service.entity.ExecutedJobEntity;
import com.chronos.executor_service.producer.JobStatusProducer;
import com.chronos.executor_service.repository.ExecutedJobRepository;
import com.chronos.executor_service.service.JobRunnerService;
import com.chronos.executor_service.service.executor.JobExecutor;
import com.chronos.executor_service.utils.JsonUtil;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobRunnerServiceImpl implements JobRunnerService {

    private static final Logger log = LoggerFactory.getLogger(JobRunnerServiceImpl.class);

    private final ExecutedJobRepository repository;
    private final JobStatusProducer statusProducer;
    private final List<JobExecutor> executors; // all beans that implement JobExecutor

    @Value("${app.kafka.topic.status:job-status-topic}")
    private String statusTopic;

    @Override
    @Async("jobExecutorPool")
    public void runJobAsync(JobRequest request) {
        String jobId = request.getJobId();
        log.info("Starting async job for jobId={} type={}", jobId, request.getJobType());

        // Create entry
        ExecutedJobEntity entity = ExecutedJobEntity.builder()
                .jobId(jobId)
                .jobType(request.getJobType())
                .payload(JsonUtil.toJson(request))
                .status("RUNNING")
                .startedAt(OffsetDateTime.now())
                .build();

        repository.save(entity);

        // send RUNNING status
        statusProducer.sendStatus(JobStatusResponse.builder().jobId(jobId).status("RUNNING").message("Job started").build());

        try {
            // find appropriate executor
            Optional<JobExecutor> optional = executors.stream()
                    .filter(e -> e.supports(request.getJobType()))
                    .findFirst();

            String logs;
            if (optional.isPresent()) {
                logs = optional.get().execute(request);
            } else {
                logs = "No executor found for jobType=" + request.getJobType();
                throw new IllegalStateException(logs);
            }

            entity.setStatus("SUCCESS");
            entity.setLogs(logs);
            entity.setCompletedAt(OffsetDateTime.now());
            repository.save(entity);

            statusProducer.sendStatus(JobStatusResponse.builder().jobId(jobId).status("SUCCESS").message(logs).build());
            log.info("Job {} finished successfully", jobId);
        } catch (Exception ex) {
            log.error("Job {} failed: {}", jobId, ex.getMessage(), ex);
            entity.setStatus("FAILED");
            entity.setLogs(ex.getMessage());
            entity.setCompletedAt(OffsetDateTime.now());
            repository.save(entity);

            statusProducer.sendStatus(JobStatusResponse.builder().jobId(jobId).status("FAILED").message(ex.getMessage()).build());
        }
    }
}
