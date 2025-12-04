package com.chronos.job_service.impl;
import com.chronos.job_service.dto.ExecutionResponse;
import com.chronos.job_service.entity.JobExecutionEntity;
import com.chronos.job_service.mapper.ExecutionMapper;
import com.chronos.job_service.repository.JobExecutionRepository;
import com.chronos.job_service.service.JobExecutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobExecutionServiceImpl implements JobExecutionService {

    private final JobExecutionRepository executionRepository;
    private final ExecutionMapper mapper;

    @Override
    public List<ExecutionResponse> getExecutionsForJob(Long jobId) {
        return executionRepository.findByJob_JobId(jobId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public ExecutionResponse getExecution(Long execId) {
        JobExecutionEntity entity = executionRepository.findById(execId)
                .orElseThrow(() -> new RuntimeException("Execution not found"));
        return mapper.toResponse(entity);
    }
}
