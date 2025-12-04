package com.chronos.scheduler_service.repository;

import com.chronos.scheduler_service.entity.ScheduledJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduledJobRepository extends JpaRepository<ScheduledJobEntity, String> {
}
