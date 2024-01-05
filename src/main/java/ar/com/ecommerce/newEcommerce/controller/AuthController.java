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
import ar.com.ecommerce.newEcommerce.utils.jwt;
import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

	@Autowired
	private UserRepository repo;
	
	public AuthController(UserRepository repo) {
		this.repo = repo;
	}
	
	@GetMapping("api/user")
	public List<User> getAllUser(HttpSession session){		
		return (List<User>) repo.findAll();
	}
	
	@PostMapping("api/auth")
	public Data authentication(@RequestBody Login login) {
		System.out.println("procesando");
		User  user = repo.getUserByEmail(login.getEmail());
		Data data = new Data();
		data.setError(true);
		data.setMessage("El email ingresado no existe");
		data.setData(null);
		
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
	
	@PostMapping("api/register")
	public User register(@RequestBody User user) {
		if (user.getId() != null) return null;
		return repo.save(user);
	}
	
	@PostMapping("api/logout")
	public ResponseEntity<?> logout(HttpSession session){
		//session.invalidate();
		return ResponseEntity.ok("session expired");
	}
}
