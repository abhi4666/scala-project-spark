package com.abhi.sparkscala;

import java.util.Properties;

import javax.mail.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailGenerator {

private Address from;
private final String Host = "pobox.mailbox,com";
private Properties prop = new Properties();
private Session session = Session.getDefaultInstance(prop, null);

private EmailGenerator() throws AddressException{
	prop.put("mail.smtp.host", Host);
	prop.put("mail.transport.protocal", "smtp");
}

public void sendEmail(String fromAddress,String[] toAddresses,String subject,String body) 
	  throws MessagingException{
	
	try {
		from = new InternetAddress(fromAddress);
		Message message = new MimeMessage(session);
		message.setFrom(from);
		
		Address[] recipientAddress = new Address[toAddresses.length];
		for(int i =0; i< recipientAddress.length; i++) {
			recipientAddress[i]= new InternetAddress(toAddresses[i]);
		}
		message.setRecipients(Message.RecipientType.TO, recipientAddress);
		message.setSubject(subject);
		//This line is what is going to make your content be seen as HTML
		message.setContent(body, "text/plain; chareset=utf=8");
		transmitEmail(message);
		
	} catch (MessagingException ex) {
		throw new MessagingException(ex.toString());
	}

}

protected void transmitEmail(Message message) throws MessagingException {
Transport.send(message);
	
	
}
}
