package com.chronos.scheduler_service.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "job-executor-topic";

    public void sendToExecutor(String jobId, String payload) {
        String message = jobId + "|" + payload;
        kafkaTemplate.send(TOPIC, message);
        log.info("Kafka: Sent job execution message = {}", message);
    }
}