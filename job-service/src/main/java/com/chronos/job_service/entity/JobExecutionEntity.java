package com.chronos.job_service.entity;

import com.chronos.job_service.entity.JobEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "job_executions")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobExecutionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long executionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private JobEntity job;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private JobScheduleEntity schedule;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String status; // SUCCESS, FAILED, TIMEOUT, CANCELLED

    @Column(columnDefinition = "TEXT")
    private String outputMessage;
}