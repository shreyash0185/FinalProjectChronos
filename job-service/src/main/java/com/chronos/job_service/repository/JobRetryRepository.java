package com.chronos.job_service.repository;

import com.chronos.job_service.entity.JobRetryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRetryRepository extends JpaRepository<JobRetryEntity, Long> {
}
