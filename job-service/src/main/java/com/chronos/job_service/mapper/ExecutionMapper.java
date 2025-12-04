package com.chronos.job_service.mapper;


import com.chronos.job_service.dto.ExecutionResponse;
import com.chronos.job_service.entity.JobExecutionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExecutionMapper {

    @Mapping(source = "job.jobId", target = "jobId")
    @Mapping(source = "schedule.scheduleId", target = "scheduleId")
    ExecutionResponse toResponse(JobExecutionEntity entity);
}
