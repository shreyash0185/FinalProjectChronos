package com.chronos.scheduler_service.dto;


import lombok.Data;

@Data
public class CreateJobRequest {
    private String cronExpression;
    private String payload;
}
