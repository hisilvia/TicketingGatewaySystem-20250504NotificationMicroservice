package com.syit.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.syit.model.TicketEvent;
import com.syit.model.TicketEventType;
import com.syit.service.EmailService;

@Component
public class TicketEventListener implements ApplicationListener<TicketEvent> {

	@Autowired
	private EmailService emailService;

	@Override
	public void onApplicationEvent(TicketEvent event) {
		String to = "";
		String subject = "";
		String body = "";
				
		if(event.getEventType() == TicketEventType.CREATED) {  //CREATED, APPROVED, REJECTED, RESOLVED, AUTOCLOSE
			to = "silviajava4@gmail.com";  //manager email
			subject = "New Ticket Created";	
			body = event.getMessage() + "\nNew ticket need to be approved: "+ event.getTicket().toString();
		}else if (event.getEventType() == TicketEventType.APPROVED) {
			to = "silviajava4@gmail.com";  //admin email
			subject = "Ticket Was Approved";	
			body = event.getMessage() + "\nApproved ticket need to be assigned: "+ event.getTicket().toString();
		}else if (event.getEventType() == TicketEventType.REJECTED) {
			to = "silviajava4@gmail.com";  //user email
			subject = "Ticket Was Rejected";	
			body = event.getMessage() + "\nThe ticket was rejected."+ event.getTicket().toString();
		}else if (event.getEventType() == TicketEventType.RESOLVED) {
			to = "silviajava4@gmail.com";  //user email
			subject = "The Ticket Was Resolved";	
			body = event.getMessage() + "\nThe ticket was resolved."+ event.getTicket().toString();
		}
		
		emailService.sendTextEmail(to, subject, body);
	}
	
}
