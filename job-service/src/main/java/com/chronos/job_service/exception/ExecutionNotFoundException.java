package com.chronos.job_service.exception;

public class ExecutionNotFoundException extends RuntimeException {
    public ExecutionNotFoundException(Long executionId) {
        super("Execution not found with id: " + executionId);
    }
}
