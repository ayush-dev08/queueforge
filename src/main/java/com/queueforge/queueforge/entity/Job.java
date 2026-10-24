package com.queueforge.queueforge.entity;

import java.time.Instant;

import com.queueforge.queueforge.job.JobStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class Job {
    protected Job(){

    }
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false,columnDefinition = "TEXT")
    private String payload;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "VARCHAR(20)")
    private JobStatus status=JobStatus.PENDING;
    private int attempts=0;
    @Column(nullable = false, updatable = false)
    private Instant createdAt;
    public Job(String type, String payload){
        this.type = type;
        this.payload = payload;
        this.status = JobStatus.PENDING;
        this.attempts = 0;
        this.createdAt = Instant.now();
    }
    public Long getId() { return id; }
    public String getType(){
        return type;
    }
    public int getAttempts(){
        return attempts;
    }
    public JobStatus getStatus(){
        return status;
    }
    public String getPayload(){
        return payload;
    }
    public Instant getCreatedAt(){
        return createdAt;
    }
}
