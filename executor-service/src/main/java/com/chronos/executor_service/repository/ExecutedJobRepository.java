package com.chronos.executor_service.repository;

import com.chronos.executor_service.entity.ExecutedJobEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface ExecutedJobRepository extends JpaRepository<ExecutedJobEntity, Long> {
    Optional<ExecutedJobEntity> findByJobId(String jobId);


}
