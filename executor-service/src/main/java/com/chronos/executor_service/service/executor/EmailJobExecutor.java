package com.chronos.executor_service.service.executor;

import com.chronos.executor_service.dto.JobRequest;
import org.slf4j.*;
import org.springframework.stereotype.Component;

@Component
public class EmailJobExecutor implements JobExecutor {

    private static final Logger log = LoggerFactory.getLogger(EmailJobExecutor.class);

    @Override
    public String execute(JobRequest request) throws Exception {
        // Simulate email send work. Replace with real email send logic.
        log.info("Executing EMAIL job: {}", request.getJobId());
        Thread.sleep(1500);
        return "EmailJob executed successfully for jobId=" + request.getJobId();
    }

    @Override
    public boolean supports(String jobType) {
        return "EMAIL".equalsIgnoreCase(jobType);
    }
}
