package com.syit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syit.model.EmailRequest;
import com.syit.service.EmailService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/notification")
@AllArgsConstructor
public class NotificationController {

	@Autowired
	EmailService emailService;
	
	/*
	@GetMapping("sendEmail/{email}")
	public ResponseEntity<String> generateAndSendPdf(@PathVariable String email){
		try {
			String response = emailService.sendTextEmail(email);
			System.out.println("sending an email....");
			return ResponseEntity.ok(response);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(500).body("Error sending email.");
		}
	}
	*/
	@PostMapping
	public ResponseEntity<?> sendMessage(@RequestBody EmailRequest request){
		try {
			emailService.sendTextEmail(request);
			return ResponseEntity.ok().build();
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
