package com.queueforge.queueforge.dto;

import java.time.Instant;

import com.queueforge.queueforge.job.JobStatus;

public record JobResponse(Long id, String type ,JobStatus status ,Instant createdAt,int attempts,Instant completedAt,String lastError) {
    
}
