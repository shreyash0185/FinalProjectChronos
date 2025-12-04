package com.chronos.scheduler_service.service;

import com.chronos.scheduler_service.jobs.GenericQuartzJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuartzJobService {

    private final SchedulerFactoryBean schedulerFactoryBean;

    public void registerJob(String jobId, String cron, String payload) {
        try {
            JobDetail jobDetail = JobBuilder.newJob(GenericQuartzJob.class)
                    .withIdentity(jobId)
                    .usingJobData("payload", payload)
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(jobId + "_trigger")
                    .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                    .build();

            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            scheduler.scheduleJob(jobDetail, trigger);

        } catch (Exception e) {
            log.error("Error registering job: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void updateTrigger(String jobId, String newCron) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobId + "_trigger");

            Trigger newTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(triggerKey)
                    .withSchedule(CronScheduleBuilder.cronSchedule(newCron))
                    .build();

            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            scheduler.rescheduleJob(triggerKey, newTrigger);

        } catch (Exception e) {
            log.error("Error updating trigger: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
