package com.queueforge.queueforge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.queueforge.queueforge.entity.Job;
import com.queueforge.queueforge.job.JobStatus;

public interface JobRepository extends JpaRepository<Job,Long>{
    Optional<Job> findFirstByStatusOrderByCreatedAtAsc(JobStatus status);
}