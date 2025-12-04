package com.chronos.job_service.service;


import com.chronos.job_service.dto.RetryResponse;

import java.util.List;

public interface JobRetryService {

    List<RetryResponse> getRetriesForExecution(Long execId);
}
