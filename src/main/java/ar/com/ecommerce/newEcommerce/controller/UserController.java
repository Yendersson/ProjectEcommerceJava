package ar.com.ecommerce.newEcommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ecommerce.newEcommerce.entities.Purchase;
import ar.com.ecommerce.newEcommerce.entities.User;
import ar.com.ecommerce.newEcommerce.entities.repository.UserRepository;

@RestController
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private UserRepository repo;
	
	public UserController(UserRepository repo) {
		this.repo = repo;
	}
	
	
	@GetMapping("api/user")
	public List<User> findAll(){
		return (List<User>) repo.findAll();
	}
	
	@GetMapping("api/user/{id}")
	public User findOneById(@PathVariable Long id){
		Optional<User> p = repo.findById(id);
		
		return p.get();
	}
	
	@PostMapping("api/user")
	public User postUser(@RequestBody User p) {
		User user = repo.save(p);
		
		return user;
	}
	
	
	@PutMapping("api/user")
	public User putUser(@RequestBody User p) {
		User user = repo.save(p);
		
		return user;
	}
	
	@DeleteMapping("api/user/{id}")
	public User deleteuser(@PathVariable Long id) {
		Optional<User> p = repo.findById(id);
		repo.delete(p.get());
		return p.get();
	}
	
	@GetMapping("api/user/{id}/purchase")
	public List<Purchase> getUserPurchases(@PathVariable Long id){
		User user = repo.findById(id).get();
		return (List<Purchase>) repo.getUserPurchases(user);
	}
}
