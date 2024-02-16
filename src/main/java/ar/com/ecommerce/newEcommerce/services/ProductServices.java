package ar.com.ecommerce.newEcommerce.services;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ecommerce.newEcommerce.entities.Product;
import ar.com.ecommerce.newEcommerce.entities.Purchase;
import ar.com.ecommerce.newEcommerce.entities.User;
import ar.com.ecommerce.newEcommerce.entities.repository.FilterEntity;
import ar.com.ecommerce.newEcommerce.entities.repository.ProductRepository;
import ar.com.ecommerce.newEcommerce.entities.repository.UserRepository;
import ar.com.ecommerce.newEcommerce.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
public class ProductServices extends ProductServicesAbstract{
	
	@Autowired
	UserRepository userDAO;
	
	public Product store(Product p, HttpSession session) {
		if (session != null) {
			Long id = Long.parseLong(session.getAttribute("user").toString());
			User u = userDAO.findById(id).get();
			if (u != null && u.getRole().equals("ADMIN")) {
				store(p);				
			}
		}
		return p;
	}
	
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
	
	public void delete(Long id, HttpSession session) {
		Product p = repo.findById(id).get();
		if (session != null) {
			Long idUser = Long.parseLong(session.getAttribute("user").toString());
			User u = userDAO.findById(idUser).get();
			if  (u != null && u.getRole().equals("ADMIN")) {
				delete(p);				
			}
		}
	}
	
	public List<Product> findAll(){
		return (List<Product>) repo.findAll();
	}
	
}
