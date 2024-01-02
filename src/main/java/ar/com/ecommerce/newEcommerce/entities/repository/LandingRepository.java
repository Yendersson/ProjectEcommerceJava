package ar.com.ecommerce.newEcommerce.entities.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.com.ecommerce.newEcommerce.entities.Landing;

@Repository
public interface LandingRepository extends CrudRepository<Landing, Long> {
	
	@Query("FROM Landing la WHERE la.active = true")
	Landing findActiveLanding();
}
