package com.queueforge.queueforge.handler;

public interface JobHandler {
    String type();
    void handle(String payload) throws Exception;
    
} 
