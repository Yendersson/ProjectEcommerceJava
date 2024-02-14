package ar.com.ecommerce.newEcommerce.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ar.com.ecommerce.newEcommerce.entities.Login;
import ar.com.ecommerce.newEcommerce.services.LoginServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginAdminController {
	
	@Autowired
	private LoginServices service;
	
	@GetMapping("login")
	public String Login(Login login, Model model) {	
		model.addAttribute("login", new Login());
		return "login";
	}
	
	@PostMapping("login")
		public String auth(Login login, HttpSession session) {
			service.store(login, session);
			
			return "redirect:/admin";
		}
	
	@GetMapping("logout")
	public String Logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
	
	
	
	
}
