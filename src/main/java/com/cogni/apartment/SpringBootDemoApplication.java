package com.cogni.apartment;

import java.util.List;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.cogni.apartment.model.ApartmentUnitBO;
import com.cogni.apartment.model.MaintenanceDTO;
import com.cogni.apartment.model.RuleBO;
import com.cogni.apartment.service.MaintenanceService;
import com.cogni.apartment.serviceImpl.MaintenanceServiceImpl;
import com.cogni.apartment.util.ApartmentDataHelper;

@SpringBootApplication
@EnableBatchProcessing
@EnableScheduling
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableWebSecurity
public class SpringBootDemoApplication {

    private final RabbitTemplate template;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}
	
	/*Code for RabbitMq*/
	
	@Autowired
    public SpringBootDemoApplication(RabbitTemplate template) {
        this.template = template;
    }

//commented for testcases    
//	@Scheduled(fixedRate = 10000)
    public void sendMessage() {
//        String timestamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
//        String message = "Hello world! " + timestamp;
        /*Fetching data and putting into queue*/
		ApartmentDataHelper dataHelper = new ApartmentDataHelper();
		List<ApartmentUnitBO> aptUnitList =  new ApartmentDataHelper().getApartmentDetailsList(); 
		Map<String,RuleBO> ruleMap =  new ApartmentDataHelper().getRuleMap();
		MaintenanceService mtncService = new MaintenanceServiceImpl();
		List<MaintenanceDTO> dtos = mtncService.getMaintenanceDtlsList(aptUnitList,ruleMap);
		for(MaintenanceDTO dto: dtos){
		System.out.println(" [x] Sent '" + dto.getKey() + "'");
        this.template.convertAndSend(dto);
		}
    }


}

