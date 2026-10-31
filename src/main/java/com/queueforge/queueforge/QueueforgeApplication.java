package com.queueforge.queueforge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableScheduling 
@SpringBootApplication
public class QueueforgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(QueueforgeApplication.class, args);
	}

}
