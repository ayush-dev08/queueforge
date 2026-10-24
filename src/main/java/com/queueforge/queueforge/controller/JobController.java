package com.queueforge.queueforge.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;
import com.queueforge.queueforge.dto.JobResponse;
import com.queueforge.queueforge.dto.SubmitJobRequest;
import com.queueforge.queueforge.service.JobService;

@RestController
@RequestMapping("/jobs")
public class JobController{
    private  JobService jobService;
    JobController(JobService jobService){
        this.jobService=jobService;
    }
    @GetMapping ("/{id}")
    public ResponseEntity<JobResponse> getJobById(@PathVariable Long id){
        return ResponseEntity.ok(jobService.getJobById(id));
    }
    @PostMapping
    public ResponseEntity<JobResponse> submitJob(@Valid @RequestBody SubmitJobRequest job){
        JobResponse jobResponse = jobService.submitJob(job);

        return ResponseEntity.status(HttpStatus.CREATED).body(jobResponse);
    }
}