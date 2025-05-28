package com.syit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.syit.service.NotificationService;

@RestController
public class NotificationController {

	@Autowired
	NotificationService notificationService;
	
	@GetMapping("sendEmail/{email}")
	public ResponseEntity<String> generateAndSendPdf(@PathVariable String email){
		try {
			String response = notificationService.sendEmail(email);
			System.out.println("sending an email....");
			return ResponseEntity.ok(response);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(500).body("Error sending email.");
		}
	}
}
