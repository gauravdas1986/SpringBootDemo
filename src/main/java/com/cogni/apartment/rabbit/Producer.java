package com.cogni.apartment.rabbit;

import java.util.List;
import java.util.Map;

import org.springframework.util.SerializationUtils;

import com.cogni.apartment.model.ApartmentUnitBO;
import com.cogni.apartment.model.MaintenanceDTO;
import com.cogni.apartment.model.RuleBO;
import com.cogni.apartment.service.MaintenanceService;
import com.cogni.apartment.serviceImpl.MaintenanceServiceImpl;
import com.cogni.apartment.util.ApartmentDataHelper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {

	private final static String QUEUE_NAME = "PRODUCER";

	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setUsername("guest");
		factory.setPassword("guest");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		/*Fetching data and putting into queue*/
		ApartmentDataHelper dataHelper = new ApartmentDataHelper();
		List<ApartmentUnitBO> aptUnitList =  new ApartmentDataHelper().getApartmentDetailsList(); 
		Map<String,RuleBO> ruleMap =  new ApartmentDataHelper().getRuleMap();
		MaintenanceService mtncService = new MaintenanceServiceImpl();
		List<MaintenanceDTO> dtos = mtncService.getMaintenanceDtlsList(aptUnitList,ruleMap);
		for(MaintenanceDTO dto: dtos){
		channel.basicPublish("", QUEUE_NAME, null, SerializationUtils.serialize(dto));
		System.out.println(" [x] Sent '" + dto.getKey() + "'");
		}
		/*Fetching data and putting into queue*/
		channel.close();
		connection.close();
	}
}