package com.syit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syit.model.EmailRequest;
import com.syit.repository.NotificationRepository;

import jakarta.mail.MessagingException;

@Service
public class EmailService {
	
	@Autowired
	NotificationRepository notificationRepo;
	
	public String sendTextEmail(EmailRequest request) {
		notificationRepo.sendSimpleEmail(request);
		
		return "Send the text body successfully.";
	}
	
	/*
	public String sendEmail(String to) throws MessagingException {
		notificationRepo.generatePdfAndSendEmail(to, "One Ticket Was Created", "Please assign the ticket.");
		
		return "send an email successfully.";
	}
	*/
}
