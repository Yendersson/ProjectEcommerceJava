package ar.com.ecommerce.newEcommerce.controller.admin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ar.com.ecommerce.newEcommerce.entities.User;
import ar.com.ecommerce.newEcommerce.entities.repository.UserRepository;
import ar.com.ecommerce.newEcommerce.utils.Utils;

@Controller
public class UserAdminController {
	
	@Autowired
	private UserRepository repo;
	
	public UserAdminController(UserRepository repo) {
		this.repo = repo;
	}
	
	@PostMapping("/admin/user")
	public String postUser(User cat) {
		
		repo.save(cat);
		
		return "redirect:/admin/user";
	}
	
	 @GetMapping("/admin/user")
		public String userListAdmin(Model model) {
			 
			model.addAttribute("users", (Collection<User>) repo.findAll());
			return "user_list";
		}
	 
	 @GetMapping("/admin/user/{id}")
	public String userAdmin(@PathVariable Long id, Model model) {
		User cat = new User();
		if (id != 0) cat = repo.findById(id).get();
		model.addAttribute("user", cat);
		return "user";
	}
	 
		@GetMapping("/admin/user/delete/{id}")
		public String deleteUser(@PathVariable Long id) {
			User p = repo.findById(id).get(); 
			repo.delete(p);
			
			return "redirect:/admin/user";
		}
}
