package com.chronos.scheduler_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "scheduled_jobs")
public class ScheduledJobEntity {

    @Id
    private String jobId;

    private String cronExpression;

    private String payload;
}
