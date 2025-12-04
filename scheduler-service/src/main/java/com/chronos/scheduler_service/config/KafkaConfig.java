package com.chronos.scheduler_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic jobExecutorTopic() {
        return TopicBuilder.name("job-executor-topic")
                .partitions(3)
                .replicas(1)
                .build();
    }
}