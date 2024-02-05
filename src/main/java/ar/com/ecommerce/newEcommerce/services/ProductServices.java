package ar.com.ecommerce.newEcommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ecommerce.newEcommerce.entities.Product;
import ar.com.ecommerce.newEcommerce.entities.repository.ProductRepository;
import jakarta.transaction.Transactional;

@Service
public class ProductServices extends ProductServicesAbstract{
	

	public Product getOneProduct(Long id) {
		return repo.findById(id).get();
	}	
	public Product find(Long id) {
		Product p = new Product();
		if (id != 0) p = repo.findById(id).get();
		return p;
	}
	
	public void delete(Long id) {
		Product p = repo.findById(id).get();
		delete(p);
	}
	
	public List<Product> findAll(){
		return (List<Product>) repo.findAll();
	}
	
}
