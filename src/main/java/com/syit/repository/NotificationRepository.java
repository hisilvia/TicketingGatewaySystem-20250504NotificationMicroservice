package com.syit.repository;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Repository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Repository
public class NotificationRepository {

private final JavaMailSender emailSender;
	
	@Autowired
	public NotificationRepository(JavaMailSender emailSender) {
		this.emailSender=emailSender;
	}
	
	public void sendEmail(String to, String subject, String text) throws MessagingException {
	//public String sendEmailWithAttachment(String to, String subject, String text, String filePath) throws MessagingException {
			
			
				
		MimeMessage message = emailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message, true); //have to set true or wont send it

	    // Set email details
	    //helper.setFrom("silviajava4@gmail.com");
	    helper.setTo(to);
	    helper.setSubject(subject);
	    helper.setText(text);

	    /*
	    // Attach the PDF file
	    File pdfFile = new File(filePath);
	    helper.addAttachment(pdfFile.getName(), pdfFile);
		*/
	    emailSender.send(message);
			    
			  
		}
}
