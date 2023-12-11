package ar.com.ecommerce.newEcommerce.entities.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.com.ecommerce.newEcommerce.entities.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long>{

}
