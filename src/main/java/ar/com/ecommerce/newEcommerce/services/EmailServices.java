package ar.com.ecommerce.newEcommerce.services;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ecommerce.newEcommerce.entities.Email;
import ar.com.ecommerce.newEcommerce.entities.repository.EmailRepository;

@Service
public class EmailServices {
	private static String host = "smtp.gmail.com";
	private static String puerto = "587";
	private static String usuario = "newcommerce235@gmail.com";
	private static String password = "jtyfhfwrdiatqbgt";
	
	@Autowired 
	private EmailRepository repo;
	
	public Message buildEmail(Email email) {
		
		Properties props = new Properties();
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.host", host);
	    props.put("mail.smtp.port", puerto);
		
		Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, password);
            }
        });
		
		Message message = new MimeMessage(session);
		
		try {
			message.setFrom(new InternetAddress("newcommerce235@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getRecipients()));
			message.setSubject(email.getSubject());
			
			if (email.getHtml()) {
				message.setContent(email.getMessage(), "text/html");
			} else {
				message.setText(email.getMessage());				
			}
			
			if (email.getSend()) Transport.send(message); 

			repo.save(email);
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return message;
	}
		
}
