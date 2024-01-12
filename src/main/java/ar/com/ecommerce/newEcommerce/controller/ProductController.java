package ar.com.ecommerce.newEcommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ecommerce.newEcommerce.entities.Banner;
import ar.com.ecommerce.newEcommerce.entities.Product;
import ar.com.ecommerce.newEcommerce.entities.repository.BannerRepository;
import ar.com.ecommerce.newEcommerce.entities.repository.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*")
public class ProductController {
	
	@Autowired
	private ProductRepository repo;
	
	@Autowired
	private BannerRepository repoBanner;
	
	public ProductController(ProductRepository repo, BannerRepository repoBanner) {
		this.repo = repo;
		this.repoBanner = repoBanner;
	}
	
	@GetMapping("api/product")
	public List<Product> findAll(HttpServletRequest req){		
		return (List<Product>) repo.findAll();
	}
	
	@GetMapping("api/product/{id}")
	public Product findOneById(@PathVariable Long id){
		
		Optional<Product> p = repo.findById(id);
		
		return p.get();
	}
	
	@PostMapping("api/product")
	public Product postProduct(@RequestBody Product p) {
		Product product = repo.save(p);
		return product;
	}
	
	@PutMapping("api/product")
	public Product putProduct(@RequestBody Product p) {
		Product product = repo.save(p);
		return product;
	}
	
	@DeleteMapping("api/product/{id}")
	public Product deleteProduct(@PathVariable Long id) {
		Optional<Product> p = repo.findById(id);
		repo.delete(p.get());
		return p.get();
	}
	
	
	@GetMapping("/update/banner")
    public List<Banner> getBanner() {
    	return (List<Banner>) repoBanner.findAll();
    }
	
	
	
}
