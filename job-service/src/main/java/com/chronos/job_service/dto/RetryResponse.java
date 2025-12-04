package com.chronos.job_service.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RetryResponse {

    private Long retryId;
    private Long executionId;
    private int retryCount;
    private String retryStatus;
    private LocalDateTime lastRetryAt;
    private LocalDateTime nextRetryAt;
}