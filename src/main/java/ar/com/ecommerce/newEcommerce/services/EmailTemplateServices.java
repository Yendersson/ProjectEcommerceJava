package ar.com.ecommerce.newEcommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.ecommerce.newEcommerce.entities.EmailTemplate;
import ar.com.ecommerce.newEcommerce.entities.repository.EmailTemplateRepository;

@Service
public class EmailTemplateServices {
	
	@Autowired
	private EmailTemplateRepository repo;
	
	public EmailTemplate findTemplate(String code) {
		return repo.findTemplateByCode(code);
	}
	
	
}
