package com.syit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.syit.model.EmailRequest;

@Service
public class EmailProducer {

	@Autowired
	JmsTemplate jmsTemplate;
	
	public void sendEmail(EmailRequest eq) {
		jmsTemplate.convertAndSend("email-queue", eq);
	}
}
