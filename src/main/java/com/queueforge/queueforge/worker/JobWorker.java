package com.queueforge.queueforge.worker;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.queueforge.queueforge.entity.Job;
import com.queueforge.queueforge.handler.JobHandler;
import com.queueforge.queueforge.service.JobExecutionService;

@Component 
public class JobWorker {
    private final JobExecutionService executionService;
    private final Map<String,JobHandler> handlers = new HashMap<>();

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(JobWorker.class);
    JobWorker(JobExecutionService jobExecutionService,List<JobHandler> handlerList){
        this.executionService=jobExecutionService;
        for (JobHandler handler : handlerList) {
            if (handlers.containsKey(handler.type())) {
                throw new IllegalStateException("Duplicate handler for type " + handler.type());
            }
            handlers.put(handler.type(), handler);
        }
    }
    @Scheduled(fixedRate = 2000)
    public void processNextJob(){
        Optional<Job> claimed =executionService.claimNextJob();
        if(claimed.isEmpty()){
            return;
        }
        Job job=claimed.get();
        Long id = job.getId();
        log.info("Claimed job {} (type={})", id, job.getType());
        JobHandler handler = handlers.get(job.getType());
        if (handler == null) {
            executionService.failJob(id, "No handler for type " + job.getType());
            log.warn("Job {} failed: no handler for type {}", id, job.getType());
            return;
        }
        try {
            handler.handle(job.getPayload());
        } catch (Exception e) {
            executionService.failJob(id, e.getMessage());
            log.error("Job {} failed: {}", id, e.getClass().getSimpleName() + ": " +e.getMessage());
            return;
        }
        executionService.completeJob(id);
        log.info("Job {} completed", id);

    }
}
