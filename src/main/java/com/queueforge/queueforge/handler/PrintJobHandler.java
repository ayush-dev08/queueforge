package com.queueforge.queueforge.handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component 
public class PrintJobHandler implements JobHandler {
    private static final Logger log = LoggerFactory.getLogger(PrintJobHandler.class);
    @Override 
    public String type(){
        return "PRINT";
    }
    @Override 
    public void handle(String payload){
        log.info("PRINT job payload: " + payload);
    }
}
