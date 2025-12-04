package com.chronos.job_service.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "jobs")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;

    private String jobName;

    private String jobType;  // EMAIL, DATA_SYNC, NOTIFICATION etc.

    private String description;

    private String createdBy;

    @Builder.Default
    private boolean active = true;

    @Column(columnDefinition = "TEXT")
    private String metadata;  // JSON inputs for job
}