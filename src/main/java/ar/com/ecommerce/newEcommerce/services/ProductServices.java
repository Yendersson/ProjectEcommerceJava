package ar.com.ecommerce.newEcommerce.services;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ecommerce.newEcommerce.entities.Product;
import ar.com.ecommerce.newEcommerce.entities.Purchase;
import ar.com.ecommerce.newEcommerce.entities.repository.FilterEntity;
import ar.com.ecommerce.newEcommerce.entities.repository.ProductRepository;
import ar.com.ecommerce.newEcommerce.utils.Utils;
import jakarta.transaction.Transactional;

@Service
public class ProductServices extends ProductServicesAbstract{
	
	public Collection<Product> filter(Map<String, String> params){
		FilterEntity<Product> productFilter = new FilterEntity<>(this.entityManager);
		Collection<Product> products = productFilter.itemsFiltered(params, Product.class.getSimpleName());
		return products; 
	}
	
	public Collection<Product> getAllProducts(Map<String, String> params){
		if (params.isEmpty() || (params.size() < 2 && params.get("page") != null)) {
			if (params.get("page") == null || "1".equals(params.get("page"))) return repo.findAllOrderByDate(0);
			return repo.findAllOrderByDate(Utils.pagination(params.get("page")));
			
		} 
		return filter(params);
	}
	
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
