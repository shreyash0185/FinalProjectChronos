package com.chronos.executor_service.consumer;

import com.chronos.executor_service.dto.JobRequest;
import com.chronos.executor_service.service.JobRunnerService;
import com.chronos.executor_service.utils.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JobExecutorConsumer {

    private static final Logger log = LoggerFactory.getLogger(JobExecutorConsumer.class);
    private final JobRunnerService jobRunnerService;

    @KafkaListener(topics = "${app.kafka.topic.execute:execute-job-topic}", groupId = "${spring.kafka.consumer.group-id:executor-group}")
    public void consume(String message) {
        log.info("Received job message: {}", message);
        try {
            JobRequest req = JsonUtil.fromJson(message, JobRequest.class);
            jobRunnerService.runJobAsync(req);
        } catch (Exception e) {
            log.error("Failed to process incoming job message", e);
            // In production, consider sending to DLQ or writing to error store
        }
    }
}
