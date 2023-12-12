package ar.com.ecommerce.newEcommerce.controller.admin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ar.com.ecommerce.newEcommerce.entities.Category;
import ar.com.ecommerce.newEcommerce.entities.Product;
import ar.com.ecommerce.newEcommerce.entities.Subcategory;
import ar.com.ecommerce.newEcommerce.entities.repository.CategoryRepository;
import ar.com.ecommerce.newEcommerce.entities.repository.ProductRepository;
import ar.com.ecommerce.newEcommerce.entities.repository.SubcategoryRepository;
import ch.qos.logback.core.model.Model;

@Controller
public class ProductAdminController {
	
	@Autowired
	private ProductRepository repo;
	private CategoryRepository repoC;
	private SubcategoryRepository repoS;
	
	public ProductAdminController(ProductRepository repo, CategoryRepository repoC, SubcategoryRepository repoS) {
		this.repo = repo;
		this.repoC = repoC;
		this.repoS = repoS;
	}
	
	@GetMapping("/admin/product")
	public String listProduct(org.springframework.ui.Model model) {
		model.addAttribute("products", (Collection<Product>) repo.findAll());
		return "product_list";
	}
	
	@GetMapping("/admin/product/{id}")
	public String listProduct(@PathVariable Long id, org.springframework.ui.Model model) {
		Product product = new Product();
		if (id != 0) product = repo.findById(id).get();

		model.addAttribute("product", product);
		model.addAttribute("category", (Collection<Category>) repoC.findAll());
		model.addAttribute("subcategory", (Collection<Subcategory>) repoS.findAll());
		
		return "product";
	}
	
	@PostMapping("/admin/product")
	public String postProduct(Product product) {
		repo.save(product);
		return  "redirect:/admin/product";
	}
	
	@GetMapping("/admin/product/delete/{id}")
	public String deleteProduct(@PathVariable Long id) {
		Product p = repo.findById(id).get(); 
		repo.delete(p);
		
		return "redirect:/admin/product";
	}
	
	
}
