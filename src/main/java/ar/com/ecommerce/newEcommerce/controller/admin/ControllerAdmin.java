package ar.com.ecommerce.newEcommerce.controller.admin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ar.com.ecommerce.newEcommerce.entities.Category;
import ar.com.ecommerce.newEcommerce.entities.Product;
import ar.com.ecommerce.newEcommerce.entities.Subcategory;
import ar.com.ecommerce.newEcommerce.entities.repository.CategoryRepository;
import ar.com.ecommerce.newEcommerce.entities.repository.ProductRepository;
import ar.com.ecommerce.newEcommerce.entities.repository.SubcategoryRepository;


@Controller
public class ControllerAdmin {
	
	@Autowired
	private ProductRepository repo;
	private CategoryRepository repoC;
	private SubcategoryRepository repoS;
	
	public ControllerAdmin(ProductRepository repo, CategoryRepository repoC, SubcategoryRepository repoS) {
		this.repo = repo;
		this.repoC = repoC;
		this.repoS = repoS;
	}
	
	 @GetMapping("/admin")
	public String inicioAdmin() {
		
		return "index";
	}
	 
	 @GetMapping("/admin/product/{id}")
	public String productAdmin(@PathVariable Long id, Model model) {
		model.addAttribute("product", repo.findById(id).get());
		model.addAttribute("category", (Collection<Category>) repoC.findAll());
		model.addAttribute("subcategory", (Collection<Subcategory>) repoS.findAll());
		return "product";
	}
	 
	 @GetMapping("/admin/product")
	public String productListAdmin(Model model) {
		 
		model.addAttribute("products", (Collection<Product>) repo.findAll());
		return "product_list";
	}
	 
	 @PostMapping("/admin/product")
	public String postProductAdmin(@RequestBody Product p) {
		System.out.println(p.getTitle());
		return "product";
	}
}
