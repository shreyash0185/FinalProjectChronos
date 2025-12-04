package com.chronos.executor_service.service.executor;

import com.chronos.executor_service.dto.JobRequest;
import org.slf4j.*;
import org.springframework.stereotype.Component;

@Component
public class CleanupJobExecutor implements JobExecutor {

    private static final Logger log = LoggerFactory.getLogger(CleanupJobExecutor.class);

    @Override
    public String execute(JobRequest request) throws Exception {
        log.info("Executing CLEANUP job: {}", request.getJobId());
        Thread.sleep(1000);
        return "Cleanup completed for jobId=" + request.getJobId();
    }

    @Override
    public boolean supports(String jobType) {
        return "CLEANUP".equalsIgnoreCase(jobType);
    }
}
