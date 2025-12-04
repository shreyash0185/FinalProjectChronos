package com.chronos.scheduler_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TriggerService {

    private final SchedulerFactoryBean schedulerFactoryBean;

    public void printAllTriggers() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        for (String group : scheduler.getTriggerGroupNames()) {
            for (TriggerKey key : scheduler.getTriggerKeys(GroupMatcher.triggerGroupEquals(group))) {
                Trigger trigger = scheduler.getTrigger(key);
                log.info("Trigger: {} - Next fire time: {}", key, trigger.getNextFireTime());
            }
        }
    }
}