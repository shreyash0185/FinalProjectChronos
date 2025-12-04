package com.chronos.scheduler_service.jobs;

import com.chronos.scheduler_service.messaging.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public class GenericQuartzJob implements Job {

    @Override
    public void execute(JobExecutionContext context) {
        try {
            String jobId = context.getJobDetail().getKey().getName();
            String payload = context.getMergedJobDataMap().getString("payload");

            KafkaProducer producer = (KafkaProducer)
                    context.getScheduler().getContext().get("kafkaProducer");

            producer.sendToExecutor(jobId, payload);

            log.info("Executed job: {}", jobId);
        } catch (Exception e) {
            log.error("Error executing job: {}", e.getMessage());
        }
    }
}
