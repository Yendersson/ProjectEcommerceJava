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

import ar.com.ecommerce.newEcommerce.entities.Ong;
import ar.com.ecommerce.newEcommerce.entities.repository.OngRepository;
import ar.com.ecommerce.newEcommerce.utils.Utils;

@Controller
public class OngAdminController {
	
	@Autowired
	private OngRepository repo;
	
	public OngAdminController(OngRepository repo) {
		this.repo = repo;
	}
	
	@PostMapping("/admin/ong")
	public String postOng(Ong cat, @RequestParam("image") MultipartFile file) {
		cat.setLogo(Utils.saveFile(file));
		repo.save(cat);
		
		return "redirect:/admin/ong";
	}
	
	 @GetMapping("/admin/ong")
		public String ongListAdmin(Model model) {
			 
			model.addAttribute("ong", (Collection<Ong>) repo.findAll());
			return "ong_list";
		}
	 
	 @GetMapping("/admin/ong/{id}")
	public String ongAdmin(@PathVariable Long id, Model model) {
		Ong cat = new Ong();
		if (id != 0) cat = repo.findById(id).get();
		model.addAttribute("ong", cat);
		return "ong";
	}
	 
		@GetMapping("/admin/ong/delete/{id}")
		public String deleteOng(@PathVariable Long id) {
			Ong p = repo.findById(id).get(); 
			repo.delete(p);
			
			return "redirect:/admin/ong";
		}

}
