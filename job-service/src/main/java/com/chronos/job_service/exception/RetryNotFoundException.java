package com.chronos.job_service.exception;


public class RetryNotFoundException extends RuntimeException {
    public RetryNotFoundException(Long retryId) {
        super("Retry not found with id: " + retryId);
    }
}
