package com.queueforge.queueforge.service;

import org.springframework.stereotype.Service;

import com.queueforge.queueforge.dto.JobResponse;
import com.queueforge.queueforge.dto.SubmitJobRequest;
import com.queueforge.queueforge.entity.Job;
import com.queueforge.queueforge.exception.ResourceNotFoundException;
import com.queueforge.queueforge.repository.JobRepository;

import org.springframework.transaction.annotation.Transactional;
@Service 
public class JobService {
    private JobRepository jobRepository;
    JobService(JobRepository jobRepository){
        this.jobRepository=jobRepository;
    }
    @Transactional 
    public JobResponse submitJob(SubmitJobRequest request){
        Job saved = jobRepository.save(new Job(request.type(), request.payload()));
        return toResponse(saved); 
    }
    @Transactional(readOnly=true)
    public JobResponse getJobById(Long id){
        Job job= jobRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Job isn't found in job id:= "+id));
        return toResponse(job);
    }
    private JobResponse toResponse(Job job) {
        return new JobResponse(job.getId(), job.getType(), job.getStatus(),
                job.getCreatedAt(), job.getAttempts());
    }
}

