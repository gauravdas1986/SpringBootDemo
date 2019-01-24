package com.cogni.apartment.util;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.cogni.apartment.model.MaintenanceDTO;

@Component
public class AccountKeeperJob extends JobExecutionListenerSupport {
	
	@Autowired
	JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	StepBuilderFactory stepBuilderFactory;
	
	/*@Value("${input.file}") 
	Resource resource;
	
	@Autowired
	Processor processor;*/
	
	@Autowired
	Writer writer;
	
	@Bean(name = "apartmentJob")
	public Job apartmentManagementJob() {

		Step step = stepBuilderFactory.get("step-1")
				.<MaintenanceDTO, MaintenanceDTO> chunk(10)
				.reader(new Reader())
//				.processor(processor)
				.writer(writer)
				.build();
		
		Job job = jobBuilderFactory.get("apartment-job")
				.incrementer(new RunIdIncrementer())
				.listener(this)
				.start(step)
				.build();

		return job;
	}
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			System.out.println("BATCH JOB COMPLETED SUCCESSFULLY");
		}
	}

}