package com.cogni.SpringBootDemo;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

	@RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
	
	@RequestMapping("/hello")
    public String hello() {
        return "Hi Gaurav...Spring Boot app is deployed in PCF!";
    }

}