package ar.com.ecommerce.newEcommerce.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ecommerce.newEcommerce.entities.Category;
import ar.com.ecommerce.newEcommerce.entities.Product;
import ar.com.ecommerce.newEcommerce.entities.Subcategory;
import ar.com.ecommerce.newEcommerce.entities.repository.CategoryRepository;
import ar.com.ecommerce.newEcommerce.entities.repository.ProductRepository;
import ar.com.ecommerce.newEcommerce.entities.repository.SubcategoryRepository;

@Service
public class ServicesXML {
	
	@Autowired(required = true)
	private ProductRepository repo;
	
	@Autowired(required = true)
	private CategoryRepository cat;
	
	@Autowired(required = true)
	private SubcategoryRepository subCat;
	
	public Product save(Product p) {
		return repo.save(p);
	}
	
	public Product findByCode(String code) {
		return repo.findOneByCode(code);
	}
	
	public Category save(Category c) {
		return cat.save(c);
	}
	
	public Subcategory save(Subcategory sub) {
		return subCat.save(sub);
	}
	
	/*public Category findByTitle(String title) {
		return cat.findOneCategory(title);
	}*/
	
	public Category findById(Long id) {
		return cat.findById(id).get();
	}

	public Subcategory findByTitle(String title) {
		return subCat.findOneCategory(title);
	}
}
