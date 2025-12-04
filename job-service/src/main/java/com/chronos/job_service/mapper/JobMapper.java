package com.chronos.job_service.mapper;

import com.chronos.job_service.dto.CreateJobRequest;
import com.chronos.job_service.dto.JobResponse;
import com.chronos.job_service.entity.JobEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JobMapper {

    JobEntity toEntity(CreateJobRequest request);

    @Mapping(source = "jobId", target = "jobId")
    JobResponse toResponse(JobEntity entity);
}
