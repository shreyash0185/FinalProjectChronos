package com.chronos.job_service.mapper;


import com.chronos.job_service.dto.RetryResponse;
import com.chronos.job_service.entity.JobRetryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RetryMapper {

    @Mapping(source = "execution.executionId", target = "executionId")
    RetryResponse toResponse(JobRetryEntity entity);
}
