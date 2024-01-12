package ar.com.ecommerce.newEcommerce.services;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.ecommerce.newEcommerce.entities.Purchase;
import ar.com.ecommerce.newEcommerce.entities.repository.PurchaseRepository;
import jakarta.transaction.Transactional;

public class PurchaseServicesAbstract {
	
	@Autowired
	protected PurchaseRepository repo;
	
	@Transactional
	public Purchase store(Purchase p) {
		return repo.save(p);
	}
}
