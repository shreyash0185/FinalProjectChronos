package com.chronos.executor_service.service.executor;

import com.chronos.executor_service.dto.JobRequest;

public interface JobExecutor {
    /**
     * Execute job. Should return a string with logs/summary.
     * Throw exception on unrecoverable error (service will mark job FAILED).
     */
    String execute(JobRequest request) throws Exception;

    boolean supports(String jobType);
}
