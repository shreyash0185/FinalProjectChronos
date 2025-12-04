package com.chronos.job_service.impl;

import com.chronos.job_service.dto.CreateJobRequest;
import com.chronos.job_service.dto.JobResponse;
import com.chronos.job_service.entity.JobEntity;
import com.chronos.job_service.exception.JobNotFoundException;
import com.chronos.job_service.mapper.JobMapper;
import com.chronos.job_service.repository.JobRepository;
import com.chronos.job_service.service.JobService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

    @Override
    @Transactional
    public JobResponse createJob(CreateJobRequest request) {
        JobEntity entity = jobMapper.toEntity(request);
        entity.setActive(true);
        entity = jobRepository.save(entity);
        return jobMapper.toResponse(entity);
    }




    @Override
    public JobResponse getJob(Long jobId) {
        JobEntity entity = jobRepository.findById(jobId)
                .orElseThrow(() -> new JobNotFoundException(jobId));
        return jobMapper.toResponse(entity);
    }

    @Override
    public List<JobResponse> getAllJobs() {
        return jobRepository.findAll()
                .stream()
                .map(jobMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public void deleteJob(Long jobId) {
        jobRepository.deleteById(jobId);
    }
}
