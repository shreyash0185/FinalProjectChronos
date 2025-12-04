package com.chronos.job_service.entity;

import com.chronos.job_service.entity.JobEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "job_schedules")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private JobEntity job;

    private String cronExpression;

    @Builder.Default
    private boolean recurring = true;

    private String timeZone;

    private String nextRunAt;

    private String lastRunAt;

    private boolean enabled = true;
}