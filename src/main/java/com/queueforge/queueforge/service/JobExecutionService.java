package com.queueforge.queueforge.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.queueforge.queueforge.entity.Job;
import com.queueforge.queueforge.exception.ResourceNotFoundException;
import com.queueforge.queueforge.job.JobStatus;
import com.queueforge.queueforge.repository.JobRepository;

import org.springframework.transaction.annotation.Transactional;
@Service
public class JobExecutionService {
    private final JobRepository jobRepository;

    JobExecutionService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Transactional
    public Optional<Job> claimNextJob() {
        Optional<Job> result = jobRepository.findFirstByStatusOrderByCreatedAtAsc(JobStatus.PENDING);

        if (result.isEmpty()) {
            return Optional.empty(); // nothing to claim
        }
        Job job = result.get();
        job.markRunning();
        jobRepository.save(job);
        return result;
    }
    @Transactional
    public void  completeJob(Long id){
        Job job = jobRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Job isn't found in job id:= "+id));
        job.markCompleted();
        jobRepository.save(job);// optional because transaction commits here -> UPDATE is written automatically
    }
    @Transactional
    public void failJob(Long id, String error){
        Job job = jobRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Job isn't found in job id:= "+id));
        job.markFailed(error);
        jobRepository.save(job);// optional because transaction commits here -> UPDATE is written automatically
    }

}
