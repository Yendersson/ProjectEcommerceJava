package ar.com.ecommerce.newEcommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ecommerce.newEcommerce.entities.Product;
import ar.com.ecommerce.newEcommerce.entities.repository.ProductRepository;
import jakarta.transaction.Transactional;

@Service
public class ProductServices {
	
	@Autowired
	private ProductRepository repo;
	
	@Transactional
	public Product store(Product p) {
		return repo.save(p);
	}
	
	public Product find(Long id) {
		return repo.findById(id).get();
	}
	
	public List<Product> findAll(){
		return (List<Product>) repo.findAll();
	}
	
}
