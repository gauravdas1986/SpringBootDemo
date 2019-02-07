package com.cogni.apartment.service;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class ConsumerService {
    @HystrixCommand(fallbackMethod = "defaultGreeting",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
      })
	
    public String getProduct(String username) {
        return new RestTemplate().getForObject("http://localhost:9090/prod_service/{username}", String.class, username);
    }

    private String defaultGreeting(String username) {
        return "Hello User!";
    }

}