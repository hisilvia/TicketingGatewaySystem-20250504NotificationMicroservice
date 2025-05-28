package com.syit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syit.repository.NotificationRepository;

import jakarta.mail.MessagingException;

@Service
public class NotificationService {
	
	@Autowired
	NotificationRepository notificationRepo;
	
	public String sendEmail(String to) throws MessagingException {
		notificationRepo.sendEmail(to, "One Ticket Was Created", "Please assign the ticket.");
		
		return "send an email successfully.";
	}

}
