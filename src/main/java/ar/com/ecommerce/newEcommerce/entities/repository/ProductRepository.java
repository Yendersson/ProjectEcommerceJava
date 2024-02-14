package ar.com.ecommerce.newEcommerce.entities.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.com.ecommerce.newEcommerce.entities.Product;
import ar.com.ecommerce.newEcommerce.entities.Purchase;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{
	
	@Query("FROM Product p WHERE p.cod = :cod")
	public Product findOneByCode(@Param("cod") String code);
	
	@Query("FROM Product p ORDER BY p.title DESC LIMIT 20 OFFSET :index")
	 Collection<Product> findAllOrderByDate(@Param("index") int index);

}
