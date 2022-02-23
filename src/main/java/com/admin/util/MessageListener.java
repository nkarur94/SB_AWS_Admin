package com.admin.util;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.admin.configuration.RabbitMQConfig;
import com.admin.entity.EMailEntity;
import com.admin.service.EMailService;

@Component
public class MessageListener {

	@Autowired
	EMailService eMailService;

	@RabbitListener(queues = RabbitMQConfig.QUEUE)
	public void onMessage(EMailEntity eMail) {

		eMailService.send(eMail.getTo(), eMail.getSubject(), eMail.getBody());

		System.out.println("Message Received:" + eMail.toString());

	}
}
