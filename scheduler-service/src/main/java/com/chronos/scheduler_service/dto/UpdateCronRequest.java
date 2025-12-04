package com.chronos.scheduler_service.dto;

import lombok.Data;

@Data
public class UpdateCronRequest {
    private String jobId;
    private String cronExpression;
}
