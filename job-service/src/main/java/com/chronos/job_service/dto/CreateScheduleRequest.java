package com.chronos.job_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CreateScheduleRequest {

    @NotNull(message = "JobId is required")
    private Long jobId;

    @NotBlank(message = "Cron expression is required")
    private String cronExpression;

    private boolean recurring = true;

    @NotBlank(message = "Time zone is required")
    private String timeZone;
}
