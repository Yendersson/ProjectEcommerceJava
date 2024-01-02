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

import ar.com.ecommerce.newEcommerce.entities.Landing;
import ar.com.ecommerce.newEcommerce.entities.Ong;
import ar.com.ecommerce.newEcommerce.entities.PaymentMethods;
import ar.com.ecommerce.newEcommerce.entities.repository.LandingRepository;
import ar.com.ecommerce.newEcommerce.entities.repository.OngRepository;
import ar.com.ecommerce.newEcommerce.entities.repository.PaymentMethodRepository;

@RestController
@CrossOrigin(origins = "*")
public class LandingController {
	
	@Autowired
	private LandingRepository repo;
	private OngRepository repoOng;
	private PaymentMethodRepository repoPayment;
	
	public LandingController(LandingRepository repo, OngRepository repoOng, PaymentMethodRepository repoPayment) {
		this.repo = repo;
		this.repoOng = repoOng;
		this.repoPayment = repoPayment;
	}
	
	
	@GetMapping("api/landing")
	public List<Landing> findAll(){
		return (List<Landing>) repo.findAll();
	}
	
	@GetMapping("api/landing/active")
	public Landing findLandingActive(){
		Landing landing = repo.findActiveLanding();
		landing.setOngs((List<Ong>) repoOng.findAll());
		landing.setPayments((List<PaymentMethods>) repoPayment.findAll());
		return  landing;
	}
	
	
	@GetMapping("api/landing/{id}")
	public Landing findOneById(@PathVariable Long id){
		
		Optional<Landing> p = repo.findById(id);
		
		return p.get();
	}
	
	@PostMapping("api/landing")
	public Landing postLanding(@RequestBody Landing p) {
		Landing Landing = repo.save(p);
		
		return Landing;
	}
	
	
	@PutMapping("api/landing")
	public Landing putLanding(@RequestBody Landing p) {
		Landing Landing = repo.save(p);
		
		return Landing;
	}
	
	@DeleteMapping("api/landing/{id}")
	public Landing deleteLanding(@PathVariable Long id) {
		Optional<Landing> p = repo.findById(id);
		repo.delete(p.get());
		return p.get();
	}
	

}
