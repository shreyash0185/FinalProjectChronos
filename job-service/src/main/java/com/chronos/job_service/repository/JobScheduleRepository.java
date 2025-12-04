package com.chronos.job_service.repository;

import com.chronos.job_service.entity.JobScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobScheduleRepository extends JpaRepository<JobScheduleEntity, Long> {

    List<JobScheduleEntity> findByEnabledTrue();
}
