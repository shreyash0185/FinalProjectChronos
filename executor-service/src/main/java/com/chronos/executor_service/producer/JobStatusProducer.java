package com.chronos.executor_service.producer;


import com.chronos.executor_service.dto.JobStatusResponse;
import com.chronos.executor_service.utils.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JobStatusProducer {

    private static final Logger log = LoggerFactory.getLogger(JobStatusProducer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${app.kafka.topic.status:job-status-topic}")
    private String statusTopic;

    public void sendStatus(JobStatusResponse status) {
        String payload = JsonUtil.toJson(status);
        log.info("Sending job status to topic {} for jobId={} payload={}", statusTopic, status.getJobId(), payload);
        kafkaTemplate.send(statusTopic, status.getJobId(), payload);
    }
}
