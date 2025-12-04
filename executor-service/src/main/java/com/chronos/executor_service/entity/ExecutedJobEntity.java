package com.chronos.executor_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "executed_jobs")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecutedJobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="job_id", nullable = false)
    private String jobId;

    @Column(name="job_type")
    private String jobType;

    @Lob
    @Column(name="payload", columnDefinition = "TEXT")
    private String payload;

    @Column(name="status")
    private String status; // PENDING, RUNNING, SUCCESS, FAILED

    @Column(name="started_at")
    private OffsetDateTime startedAt;

    @Column(name="completed_at")
    private OffsetDateTime completedAt;

    @Lob
    @Column(name="logs", columnDefinition = "TEXT")
    private String logs;
}
