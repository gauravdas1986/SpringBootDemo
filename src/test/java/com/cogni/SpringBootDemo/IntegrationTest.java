package com.cogni.SpringBootDemo;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.cogni.apartment.SpringBootDemoApplication;
import com.cogni.apartment.model.MaintenanceDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = { SpringBootDemoApplication.class })
public class IntegrationTest {
	RestTemplate restTemplate;
	String apiUrl = "http://localhost:8080/aptservice/";

	public IntegrationTest(){
        restTemplate = new RestTemplate();
    }

	@Test
	public void getAllMaintenanceData() throws RestClientException, URISyntaxException{
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity<String>(headers);
        ResponseEntity<MaintenanceDTO[]> response =restTemplate.exchange(new URI(apiUrl+"get_maintenance_data"), HttpMethod.GET, request, MaintenanceDTO[].class);
		
		
	}
}
