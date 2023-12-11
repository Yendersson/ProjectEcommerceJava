package ar.com.ecommerce.newEcommerce.entities.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.com.ecommerce.newEcommerce.entities.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{

}
