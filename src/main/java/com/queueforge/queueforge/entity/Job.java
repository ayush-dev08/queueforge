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
    private Instant completedAt;
    @Column(columnDefinition = "TEXT")
    private String lastError;
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
    public String getLastError(){
        return lastError;
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
    public Instant getCompletedAt(){
        return completedAt;
    }
    public void markRunning(){
        if(this.status!=JobStatus.PENDING){
            throw new IllegalStateException("Cannot start job "+id+" from status "+this.status);
        }
        this.status=JobStatus.RUNNING;
        this.attempts+=1;
    }
    public void markCompleted(){
        if(this.status!=JobStatus.RUNNING){
            throw new IllegalStateException("Cannot complete job "+id+" from status "+this.status);
        }
        this.status=JobStatus.COMPLETED;
        this.completedAt=Instant.now();
    }
    public void markFailed(String error){
        if(this.status!=JobStatus.RUNNING){
            throw new IllegalStateException("Cannot fail job "+id+" from status "+this.status);
        }
        this.status=JobStatus.FAILED;
        this.lastError=(error == null || error.isBlank()) ? "Unknown error" : error;
        this.completedAt=Instant.now();

    }
}
