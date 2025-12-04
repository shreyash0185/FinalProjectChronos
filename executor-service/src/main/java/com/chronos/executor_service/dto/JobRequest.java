package com.chronos.executor_service.dto;


import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobRequest {
    private String jobId;
    private String jobType;
    private Map<String, Object> params;
}