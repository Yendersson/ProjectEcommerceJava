package ar.com.ecommerce.newEcommerce.services;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.ecommerce.newEcommerce.entities.Login;
import ar.com.ecommerce.newEcommerce.entities.repository.UserRepository;

public class LoginServiceAbstract {
		
	@Autowired
	protected UserRepository repo;
	
	public Object store(Login login) {
	
		return login;
	}
}
