package ar.com.ecommerce.newEcommerce.entities.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.com.ecommerce.newEcommerce.entities.Subcategory;

@Repository
public interface SubcategoryRepository extends CrudRepository<Subcategory, Long>{

	@Query("FROM Subcategory s where s.title = :title")
	Subcategory findOneCategory(@Param("title") String title);

}
