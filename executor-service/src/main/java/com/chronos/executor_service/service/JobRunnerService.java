package com.chronos.executor_service.service;

import com.chronos.executor_service.dto.JobRequest;

public interface JobRunnerService {
    void runJobAsync(JobRequest request);
}