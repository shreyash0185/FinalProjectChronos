package com.chronos.job_service.exception;

public class JobNotFoundException extends RuntimeException {
    public JobNotFoundException(Long jobId) {
        super("Job not found with id: " + jobId);
    }
}
