package ar.com.ecommerce.newEcommerce.services;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.ecommerce.newEcommerce.entities.User;
import ar.com.ecommerce.newEcommerce.entities.repository.UserRepository;
import jakarta.transaction.Transactional;

public abstract class UserServiceAbstract {
	
	@Autowired
	protected UserRepository repo;
	
	@Transactional
	public User store(User u) {
		return repo.save(u);
	}
	
	public void delete(User u) {
		repo.delete(u);
	}
}
