package ar.com.ecommerce.newEcommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ecommerce.newEcommerce.entities.Data;
import ar.com.ecommerce.newEcommerce.entities.Login;
import ar.com.ecommerce.newEcommerce.entities.User;
import ar.com.ecommerce.newEcommerce.entities.repository.UserRepository;
import ar.com.ecommerce.newEcommerce.services.UserServices;
import ar.com.ecommerce.newEcommerce.utils.jwt;
import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

	@Autowired
	private UserRepository repo;
	
	@Autowired 
	private UserServices service;
	
	@PostMapping("api/auth")
	public Data authentication(@RequestBody Login login) {
		return service.authenticate(login);
		
	}
	
	@PostMapping("api/register")
	public User register(@RequestBody User user) {
		return service.store(user);
	}
	
	@PostMapping("api/logout")
	public ResponseEntity<?> logout(HttpSession session){
		//session.invalidate();
		return ResponseEntity.ok("session expired");
	}
}
