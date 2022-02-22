package com.admin.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.configuration.RabbitMQConfig;

@RestController
@RequestMapping("/rabbit")
public class RabbitMqController {
	
	
	
	@Autowired
	RabbitTemplate template;
	
	@GetMapping("/send")
	public String message() {
		
		String message = "hello guys";
		String message1 = "hello guy 2";

		template.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, message);
		template.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, message1);

		return "success";
		
	}

}
