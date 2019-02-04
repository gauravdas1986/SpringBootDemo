package com.cogni.apartment.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	private final RestTemplate restTemplate;
	
	@Value("${spring.application.host}")
	private String hostname;
	
	public ScheduledTasks(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	
//	Commented for rabbit test
//	@Scheduled(fixedRate = 200000)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
        this.restTemplate.getForObject(hostname+"/aptservice/run-batch-job", String.class);
        /*this.restTemplate.exchange("/aptservice/run-batch-job",HttpMethod.GET);
        this.restTemplate.getForEntity("/aptservice/run-batch-job",String.class);*/
        
        
    }
	
}