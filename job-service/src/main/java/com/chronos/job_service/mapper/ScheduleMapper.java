package com.chronos.job_service.mapper;

import com.chronos.job_service.dto.CreateScheduleRequest;
import com.chronos.job_service.dto.ScheduleResponse;
import com.chronos.job_service.entity.JobScheduleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    @Mapping(source = "jobId", target = "job.jobId")
    JobScheduleEntity toEntity(CreateScheduleRequest request);

    @Mapping(source = "job.jobId", target = "jobId")
    ScheduleResponse toResponse(JobScheduleEntity entity);
}
