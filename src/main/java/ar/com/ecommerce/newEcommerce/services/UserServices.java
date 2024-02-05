package ar.com.ecommerce.newEcommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ecommerce.newEcommerce.entities.Data;
import ar.com.ecommerce.newEcommerce.entities.Email;
import ar.com.ecommerce.newEcommerce.entities.EmailTemplate;
import ar.com.ecommerce.newEcommerce.entities.Login;
import ar.com.ecommerce.newEcommerce.entities.PurchaseProduct;
import ar.com.ecommerce.newEcommerce.entities.User;
import ar.com.ecommerce.newEcommerce.entities.repository.UserRepository;
import ar.com.ecommerce.newEcommerce.utils.jwt;
import jakarta.transaction.Transactional;

@Service
public class UserServices extends UserServiceAbstract{
	
	@Autowired
	private EmailServices service;
	
	@Autowired
	private EmailTemplateServices serviceTemplate;
	
	@Override
	public User store(User u) {
		if (u.getId() != null) return null;
		
		buildTemplate(u);
		return super.store(u);
	}
	
	public Data authenticate(Login login) {
		Data data = new Data();
		data.setError(true);
		data.setMessage("El email ingresado no existe");
		data.setData(null);
		
		User user = repo.getUserByEmail(login.getEmail());
		
		if (user == null) return data;
		
		if (!user.getPassword().equals(login.getPassword()))  {
			data.setMessage("La clave no coincide con el email agregado");
			return data;
		};
		
		data.setError(false);
		data.setMessage("Inicio de session exitoso");
		data.setData(jwt.generateToken(user));
		
		return data;
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
