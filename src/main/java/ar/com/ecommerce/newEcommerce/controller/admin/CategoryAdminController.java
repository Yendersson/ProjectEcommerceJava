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

import ar.com.ecommerce.newEcommerce.entities.Category;
import ar.com.ecommerce.newEcommerce.entities.repository.CategoryRepository;
import ar.com.ecommerce.newEcommerce.utils.Utils;

@Controller
public class CategoryAdminController {
	
	@Autowired
	private CategoryRepository repo;
	
	public CategoryAdminController(CategoryRepository repo) {
		this.repo = repo;
	}
	
	@PostMapping("/admin/category")
	public String postCategory(Category cat, @RequestParam("image") MultipartFile file) {
		cat.setPicture(Utils.saveFile(file));
		repo.save(cat);
		
		return "redirect:/admin/category";
	}
	
	 @GetMapping("/admin/category")
		public String categoryListAdmin(Model model) {
			 
			model.addAttribute("categories", (Collection<Category>) repo.findAll());
			return "category_list";
		}
	 
	 @GetMapping("/admin/category/{id}")
	public String categoryAdmin(@PathVariable Long id, Model model) {
		Category cat = new Category();
		if (id != 0) cat = repo.findById(id).get();
		model.addAttribute("category", cat);
		return "Category";
	}
	 
		@GetMapping("/admin/category/delete/{id}")
		public String deleteCategory(@PathVariable Long id) {
			Category p = repo.findById(id).get(); 
			repo.delete(p);
			
			return "redirect:/admin/category";
		}
	 
	
	
}	
