package com.chronos.job_service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExecutionResponse {

    private Long executionId;
    private Long jobId;
    private Long scheduleId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
    private String outputMessage;
}
