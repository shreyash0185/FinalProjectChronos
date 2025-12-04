package com.chronos.executor_service.service.executor;

import com.chronos.executor_service.dto.JobRequest;
import org.slf4j.*;
import org.springframework.stereotype.Component;

@Component
public class ReportJobExecutor implements JobExecutor {

    private static final Logger log = LoggerFactory.getLogger(ReportJobExecutor.class);

    @Override
    public String execute(JobRequest request) throws Exception {
        // Simulate report generation
        log.info("Executing REPORT job: {}", request.getJobId());
        Thread.sleep(3000);
        return "Report generated successfully for jobId=" + request.getJobId();
    }

    @Override
    public boolean supports(String jobType) {
        return "REPORT".equalsIgnoreCase(jobType);
    }
}
