package ar.com.ecommerce.newEcommerce.entities.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.com.ecommerce.newEcommerce.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	@Query("FROM User u WHERE email = :email")
	User getUserByEmail(@Param("email")String email);
}
