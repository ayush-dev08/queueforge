package com.queueforge.queueforge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.queueforge.queueforge.entity.Job;

public interface JobRepository extends JpaRepository<Job,Long>{
    
}