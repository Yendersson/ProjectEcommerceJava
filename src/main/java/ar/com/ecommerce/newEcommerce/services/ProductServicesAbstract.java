package ar.com.ecommerce.newEcommerce.services;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.ecommerce.newEcommerce.entities.Product;
import ar.com.ecommerce.newEcommerce.entities.repository.ProductRepository;
import jakarta.transaction.Transactional;

public abstract class ProductServicesAbstract {
	
	@Autowired
	protected ProductRepository repo;
	
	@Transactional
	public Product store(Product p) {
		return repo.save(p);
	}
	
	public void delete(Product p) {
		repo.delete(p);
	}
}
