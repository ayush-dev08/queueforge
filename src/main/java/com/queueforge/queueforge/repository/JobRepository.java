package com.queueforge.queueforge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

import com.queueforge.queueforge.entity.Job;
import com.queueforge.queueforge.job.JobStatus;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;

public interface JobRepository extends JpaRepository<Job,Long>{


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints(@QueryHint(name = "jakarta.persistence.lock.timeout",value = "-2"))
    Optional<Job> findFirstByStatusOrderByCreatedAtAsc(JobStatus status);
}