package com.chronos.job_service.repository;

import com.chronos.job_service.entity.JobExecutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobExecutionRepository extends JpaRepository<JobExecutionEntity, Long> {

    List<JobExecutionEntity> findByJob_JobId(Long jobId);

    List<JobExecutionEntity> findBySchedule_ScheduleId(Long scheduleId);
}
