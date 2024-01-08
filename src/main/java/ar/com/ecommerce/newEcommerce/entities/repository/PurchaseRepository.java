package ar.com.ecommerce.newEcommerce.entities.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.com.ecommerce.newEcommerce.entities.Purchase;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Long>{

	@Query("FROM Purchase p ORDER BY p.date DESC")
	 Collection<Purchase> findAllOrderByDate();
}
