package com.chronos.job_service.dto;


import lombok.Data;

@Data
public class JobResponse {

    private Long jobId;
    private String jobName;
    private String jobType;
    private String description;
    private boolean active;
    private String metadata;
}
