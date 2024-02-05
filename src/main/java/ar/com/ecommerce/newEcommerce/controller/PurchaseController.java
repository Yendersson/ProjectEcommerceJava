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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ecommerce.newEcommerce.entities.Purchase;
import ar.com.ecommerce.newEcommerce.entities.repository.PurchaseRepository;
import ar.com.ecommerce.newEcommerce.entities.repository.UserRepository;
import ar.com.ecommerce.newEcommerce.utils.jwt;

@RestController
public class PurchaseController {
	
	@Autowired
	private PurchaseRepository repo;
	
	public PurchaseController(PurchaseRepository repo) {
		this.repo = repo;
	}
	
	
	@GetMapping("api/purchase")
	public List<Purchase> findAll(){
		return (List<Purchase>) repo.findAll();
	}
	
	@GetMapping("api/purchase/user")
	public List<Purchase> findUserPurchase(@RequestHeader("Authorization") String token){
		return (List<Purchase>) repo.findByUser(jwt.getIdFromToken(token.substring(7)));
	}
	
	@GetMapping("api/purchase/{id}")
	public Purchase findOneById(@PathVariable Long id){
		
		Optional<Purchase> p = repo.findById(id);
		
		return p.get();
	}
	
	@PostMapping("api/purchase")
	public Purchase postPurchase(@RequestBody Purchase p) {
		Purchase Purchase = repo.save(p);
		
		return Purchase;
	}
	
	
	@PutMapping("api/purchase")
	public Purchase putPurchase(@RequestBody Purchase p) {
		Purchase Purchase = repo.save(p);
		
		return Purchase;
	}
	
	@DeleteMapping("api/purchase/{id}")
	public Purchase deletePurchase(@PathVariable Long id) {
		Optional<Purchase> p = repo.findById(id);
		repo.delete(p.get());
		return p.get();
	}

}
