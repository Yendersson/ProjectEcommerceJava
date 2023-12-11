package ar.com.ecommerce.newEcommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ecommerce.newEcommerce.entities.Category;
import ar.com.ecommerce.newEcommerce.entities.repository.CategoryRepository;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryRepository repo;
	
	public CategoryController(CategoryRepository repo) {
		this.repo = repo;
	}
	
	
	@GetMapping("api/category")
	public List<Category> findAll(){
		return (List<Category>) repo.findAll();
	}
	
	@GetMapping("api/category/{id}")
	public Category findOneById(@PathVariable Long id){
		
		Optional<Category> p = repo.findById(id);
		
		return p.get();
	}
	
	@PostMapping("api/category")
	public Category postCategory(@RequestBody Category p) {
		Category Category = repo.save(p);
		
		return Category;
	}
	
	
	@PutMapping("api/category")
	public Category putCategory(@RequestBody Category p) {
		Category Category = repo.save(p);
		
		return Category;
	}
	
	@DeleteMapping("api/category/{id}")
	public Category deleteCategory(@PathVariable Long id) {
		Optional<Category> p = repo.findById(id);
		repo.delete(p.get());
		return p.get();
	}
	
}
