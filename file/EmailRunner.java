package com.syit.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.syit.model.EmailRequest;
import com.syit.service.EmailProducer;

@Component
public class EmailRunner implements CommandLineRunner {
	
	@Autowired
	EmailProducer emailProducer;

	@Override
	public void run(String... args) throws Exception {

		EmailRequest email = new EmailRequest();
		email.setRecipientEmail("silviajava4@gmail.com");
		email.setSubject("Test Email");
		email.setMessage("This is a test email11 from ActiveMQ");
		emailProducer.sendEmail(email);
		
	}

	
}
