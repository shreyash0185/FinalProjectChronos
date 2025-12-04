package com.chronos.job_service.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "job_retries")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobRetryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long retryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "execution_id")
    private JobExecutionEntity execution;

    private int retryCount;

    private String retryStatus;

    private LocalDateTime lastRetryAt;

    private LocalDateTime nextRetryAt;

    private int maxRetryAllowed;

    private String backoffStrategy; // FIXED, EXPONENTIAL
}