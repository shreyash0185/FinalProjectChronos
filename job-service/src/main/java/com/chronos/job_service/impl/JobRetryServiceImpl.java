package com.chronos.job_service.impl;

import com.chronos.job_service.dto.RetryResponse;
import com.chronos.job_service.mapper.RetryMapper;
import com.chronos.job_service.repository.JobRetryRepository;
import com.chronos.job_service.service.JobRetryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobRetryServiceImpl implements JobRetryService {

    private final JobRetryRepository retryRepository;
    private final RetryMapper mapper;

    @Override
    public List<RetryResponse> getRetriesForExecution(Long execId) {
        return retryRepository.findAll()
                .stream()
                .filter(r -> r.getExecution().getExecutionId().equals(execId))
                .map(mapper::toResponse)
                .toList();
    }
}