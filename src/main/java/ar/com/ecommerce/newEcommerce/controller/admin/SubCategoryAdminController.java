package ar.com.ecommerce.newEcommerce.controller.admin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ar.com.ecommerce.newEcommerce.entities.Category;
import ar.com.ecommerce.newEcommerce.entities.Product;
import ar.com.ecommerce.newEcommerce.entities.Subcategory;
import ar.com.ecommerce.newEcommerce.entities.repository.CategoryRepository;
import ar.com.ecommerce.newEcommerce.entities.repository.SubcategoryRepository;

@Controller
public class SubCategoryAdminController {
	
	@Autowired
	private SubcategoryRepository repoS;
	
	@Autowired
	private CategoryRepository repoC;
	
	public SubCategoryAdminController(CategoryRepository repoC, SubcategoryRepository repoS) {
		this.repoC = repoC;
		this.repoS = repoS;
	}
	
	@PostMapping("/admin/subcategory")
	public String postSubCategory(Subcategory subcategory) {
		
		repoS.save(subcategory);
		
		return "redirect:/admin/subcategory";
	}
	
	 @GetMapping("/admin/subcategory/{id}")
	public String subCategoryAdmin(@PathVariable Long id, Model model) {
		 Subcategory subcategory = new Subcategory();
		 if (id != 0) subcategory = repoS.findById(id).get();
		 
		model.addAttribute("categories", (Collection<Category>) repoC.findAll());
	 	model.addAttribute("subcategory", subcategory);
		return "subcategory";
	}
	 
	 @GetMapping("/admin/subcategory")
	public String subCcategoryListAdmin(Model model) {
		 
		model.addAttribute("subcategories", (Collection<Subcategory>) repoS.findAll());
		return "subcategory_list";
	}
	 
		@GetMapping("/admin/subcategory/delete/{id}")
		public String deleteProduct(@PathVariable Long id) {
			Subcategory p = repoS.findById(id).get(); 
			repoS.delete(p);
			
			return "redirect:/admin/subcategory";
		}

}
