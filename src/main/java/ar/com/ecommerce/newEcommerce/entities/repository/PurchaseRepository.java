package ar.com.ecommerce.newEcommerce.entities.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.com.ecommerce.newEcommerce.entities.Purchase;
import ar.com.ecommerce.newEcommerce.entities.User;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Long>{

	@Query("FROM Purchase p ORDER BY p.date DESC LIMIT 20 OFFSET :index")
	 Collection<Purchase> findAllOrderByDate(@Param("index") int index);
	
	@Query("FROM Purchase p WHERE p.user.id = :user ORDER BY p.date DESC")
	Collection<Purchase> findByUser(@Param("user") String user);
}
