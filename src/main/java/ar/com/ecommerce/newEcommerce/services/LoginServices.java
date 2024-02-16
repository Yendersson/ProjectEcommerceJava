package ar.com.ecommerce.newEcommerce.services;

import org.springframework.stereotype.Service;

import ar.com.ecommerce.newEcommerce.entities.Login;
import ar.com.ecommerce.newEcommerce.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Service
public class LoginServices extends LoginServiceAbstract{
	
	public Object store (Login login, HttpSession session) {
		User u = repo.getUserByEmail(login.getEmail());
		 
		 if (u == null) {
			 return "Email invalido";
		 }
		 
		 if (!u.getPassword().equals(login.getPassword())) {
			 return "Clave no coincide";
		 }
		 System.out.println("Exists");
		 
		 session.setAttribute("user", u.getId());
		
		return 1L;
	}
}
