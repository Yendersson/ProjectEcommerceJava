package ar.com.ecommerce.newEcommerce.entities.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.com.ecommerce.newEcommerce.entities.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long>{

	@Query("FROM Category c WHERE c.title = :title")
	public Category findOneCategory(@Param("title") String title);
}
