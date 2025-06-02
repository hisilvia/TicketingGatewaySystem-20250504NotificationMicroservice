package com.syit.repository;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Repository;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.syit.model.EmailRequest;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@Repository
public class NotificationRepository {

	@Autowired
	private JavaMailSender emailSender;
	
	@Value("${spring.mail.username}")
	private String senderEmail;
	
	//Only send simple text
	public void sendSimpleEmail(EmailRequest request) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(senderEmail);
		message.setTo(request.getRecipientEmail());
		message.setSubject(request.getSubject());
		message.setText(request.getMessage());
		emailSender.send(message);
		//log.info("{} is expecting your email.");
	}
	
	//*****************************************************
	//1.generate Pdf and save it
	public String generateAndSavePdf(String content, String filePath) throws IOException {
		Document document = new Document();

	    FileOutputStream fileOutputStream = new FileOutputStream(filePath);

	    try {
	        PdfWriter.getInstance(document, fileOutputStream);

	        document.open();
	        document.add(new Paragraph("Dynamic PDF Content"));
	        document.add(new Paragraph(content));

	        document.close();
	    } catch (Exception e) {
	        System.out.println("Error:"+e);
	    } finally {
	        fileOutputStream.close();
	    }

	    return filePath;
	}
	
	//2.send an email with pdf
	
	
	public void generatePdfAndSendEmail(String to, String subject, String body) throws MessagingException {
	//public void generatePdfAndSendEmail(String to, String subject, String body, String filePath) throws MessagingException {			
		MimeMessage message = emailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message, true); //have to set true or wont send it

	    // Set email details
	    //helper.setFrom("silviajava4@gmail.com");
	    helper.setTo(to);
	    helper.setSubject(subject);
	    helper.setText(body);

	    /*
	    // Attach the PDF file
	    File pdfFile = new File(filePath);
	    helper.addAttachment(pdfFile.getName(), pdfFile);
		*/
	    emailSender.send(message);
			    
			  
	}
	

}
