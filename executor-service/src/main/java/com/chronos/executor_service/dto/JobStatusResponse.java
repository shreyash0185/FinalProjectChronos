package com.chronos.executor_service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobStatusResponse {
    private String jobId;
    private String status; // RUNNING, SUCCESS, FAILED
    private String message;
}
