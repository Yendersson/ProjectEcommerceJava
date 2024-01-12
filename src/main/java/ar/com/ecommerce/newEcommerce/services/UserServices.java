package ar.com.ecommerce.newEcommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ecommerce.newEcommerce.entities.Email;
import ar.com.ecommerce.newEcommerce.entities.EmailTemplate;
import ar.com.ecommerce.newEcommerce.entities.PurchaseProduct;
import ar.com.ecommerce.newEcommerce.entities.User;
import ar.com.ecommerce.newEcommerce.entities.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class UserServices {
		
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private EmailServices service;
	
	@Autowired
	private EmailTemplateServices serviceTemplate;
	
	@Transactional
	public User store(User u) {
		
		if (u.getId() == null || !repo.existsById(u.getId())) buildTemplate(u);
		
		return repo.save(u);
	}
	
	public void buildTemplate(User u) {
		EmailTemplate template = serviceTemplate.findTemplate("RA");
		Email email = new Email();
		email.setHtml(false);
		
		email.setSubject(template.getSubject().replaceAll("USERNAME", u.getUsername()));
		email.setMessage(template.getBody().replaceAll("USERNAME", u.getUsername()).replaceAll("PASSWORD", u.getPassword()));
		email.setRecipients(u.getEmail());
		email.setSend(true);
		
		service.buildEmail(email);
	}
	
	
	
}
