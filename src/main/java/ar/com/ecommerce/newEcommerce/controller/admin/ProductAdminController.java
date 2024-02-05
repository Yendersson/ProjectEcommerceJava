package ar.com.ecommerce.newEcommerce.controller.admin;

import java.io.File;
import java.net.http.HttpRequest;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import ar.com.ecommerce.newEcommerce.entities.Category;
import ar.com.ecommerce.newEcommerce.entities.Product;
import ar.com.ecommerce.newEcommerce.entities.Subcategory;
import ar.com.ecommerce.newEcommerce.entities.repository.CategoryRepository;
import ar.com.ecommerce.newEcommerce.entities.repository.ProductRepository;
import ar.com.ecommerce.newEcommerce.entities.repository.SubcategoryRepository;
import ar.com.ecommerce.newEcommerce.services.ProductServices;
import ar.com.ecommerce.newEcommerce.utils.Utils;
import ch.qos.logback.core.model.Model;

@Controller
public class ProductAdminController {
	
	@Autowired
	private ProductServices service;
	@Autowired
	private CategoryRepository repoC;
	@Autowired
	private SubcategoryRepository repoS;
	
	@GetMapping("/admin/product")
	public String listProduct(org.springframework.ui.Model model) {
		model.addAttribute("products", (Collection<Product>) service.findAll());
		return "product_list";
	}
	
	@GetMapping("/admin/product/{id}")
	public String listProduct(@PathVariable Long id, org.springframework.ui.Model model) {
		model.addAttribute("product", service.getOneProduct(id));
		model.addAttribute("category", (Collection<Category>) repoC.findAll());
		model.addAttribute("subcategory", (Collection<Subcategory>) repoS.findAll());
		
		return "product";
	}
	
	@PostMapping("/admin/product")
	public String postProduct(Product product, @RequestParam("image") MultipartFile file) {
		if (file.getSize() != 0) product.setPicture(Utils.saveFile(file)); 
		System.out.println(product);
		service.store(product);
		return "redirect:/admin/product";
	}
	
	@GetMapping("/admin/product/delete/{id}")
	public String deleteProduct(@PathVariable Long id) {
		service.delete(id);
		return "redirect:/admin/product";
	}
	
	
}
