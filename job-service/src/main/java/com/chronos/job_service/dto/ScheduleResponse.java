package com.chronos.job_service.dto;

import lombok.Data;

@Data
public class ScheduleResponse {

    private Long scheduleId;
    private Long jobId;
    private String cronExpression;
    private boolean recurring;
    private String timeZone;
    private String nextRunAt;
    private String lastRunAt;
}
