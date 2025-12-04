package com.chronos.job_service.service;

import com.chronos.job_service.dto.ExecutionResponse;

import java.util.List;

public interface JobExecutionService {

    List<ExecutionResponse> getExecutionsForJob(Long jobId);

    ExecutionResponse getExecution(Long execId);
}
