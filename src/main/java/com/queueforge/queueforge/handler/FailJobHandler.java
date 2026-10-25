package com.queueforge.queueforge.handler;

import org.springframework.stereotype.Component;

@Component 
public class FailJobHandler implements JobHandler{
    @Override 
    public String type(){
        return "FAIL";
    }
    @Override 
    public void handle(String payload){
        throw new RuntimeException("boom");
    }
}
