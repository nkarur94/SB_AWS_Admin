package com.admin.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.configuration.RabbitMQConfig;
import com.admin.entity.EMailEntity;

@RestController
@RequestMapping("/rabbit")
public class RabbitMqController {
	
	
	
	@Autowired
	RabbitTemplate templates;
	
	@GetMapping("/send")
	public String message(@RequestBody EMailEntity email) {
		templates.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, email);
		System.out.println(email);
		return "success";
		
	}
	
	@PostMapping("/many")
	public String sendMsg(@RequestBody EMailEntity email) {
		for(int i=0;i<=10;i++) {
			
			
			//producer-this method produces the request  to the rabbitMQ functionalities using convertAndSend method of RabbitTemplate class 
			templates.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, email);//EXCHANGE, ROTING_KEY and email of EmailEntity class as parameters.

		}
		return "success";
	}
	

}
