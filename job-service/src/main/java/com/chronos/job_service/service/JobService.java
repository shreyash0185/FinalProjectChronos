package com.chronos.job_service.service;


import com.chronos.job_service.dto.CreateJobRequest;
import com.chronos.job_service.dto.JobResponse;

import java.util.List;

public interface JobService {

    JobResponse createJob(CreateJobRequest request);

    JobResponse getJob(Long jobId);

    List<JobResponse> getAllJobs();

    void deleteJob(Long jobId);
}