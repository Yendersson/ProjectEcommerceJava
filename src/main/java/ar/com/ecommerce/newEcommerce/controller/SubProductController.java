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

import ar.com.ecommerce.newEcommerce.entities.Subcategory;
import ar.com.ecommerce.newEcommerce.entities.repository.SubcategoryRepository;

@RestController
@CrossOrigin(origins = "*")
public class SubProductController {
	@Autowired
	private SubcategoryRepository repo;
	
	public SubProductController(SubcategoryRepository repo) {
		this.repo = repo;
	}
	
	
	@GetMapping("api/subcategory")
	public List<Subcategory> findAll(){
		return (List<Subcategory>) repo.findAll();
	}
	
	@GetMapping("api/subcategory/{id}")
	public Subcategory findOneById(@PathVariable Long id){
		
		Optional<Subcategory> p = repo.findById(id);
		
		return p.get();
	}
	
	@PostMapping("api/subcategory")
	public Subcategory postSubcategory(@RequestBody Subcategory p) {
		Subcategory Subcategory = repo.save(p);
		
		return Subcategory;
	}
	
	
	@PutMapping("api/subcategory")
	public Subcategory putSubcategory(@RequestBody Subcategory p) {
		Subcategory Subcategory = repo.save(p);
		
		return Subcategory;
	}
	
	@DeleteMapping("api/subcategory/{id}")
	public Subcategory deleteSubcategory(@PathVariable Long id) {
		Optional<Subcategory> p = repo.findById(id);
		repo.delete(p.get());
		return p.get();
	}

}
