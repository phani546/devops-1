package com.devops.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value="/helloworld")
public class DemoController {
	
	@Autowired
	private Environment environment;
	
	@GetMapping(path="/health", produces= MediaType.TEXT_PLAIN_VALUE)
	public String getServiceStatus() {
		return "service running";
	}
	
	@GetMapping(path="/hi/{greetings}", produces= MediaType.TEXT_PLAIN_VALUE)
	public String sayHello(@PathVariable String greetings) {
		System.out.println("I am loaded with : " +  environment.getProperty("env"));
		return "Hello  " + greetings + " I am loaded " + environment.getProperty("env");
	}
}