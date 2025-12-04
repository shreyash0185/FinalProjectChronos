package com.chronos.executor_service.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Executor Service API", version = "1.0", description = "APIs to view job executions"))
public class SwaggerConfig {
}
