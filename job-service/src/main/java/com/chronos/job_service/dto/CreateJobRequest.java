package com.chronos.job_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateJobRequest {

    @NotBlank(message = "Job name is required")
    private String jobName;

    @NotBlank(message = "Job type is required")
    private String jobType;

    private String description;

    @NotBlank(message = "CreatedBy is required")
    private String createdBy;

    private String metadata;  // JSON payload
}
