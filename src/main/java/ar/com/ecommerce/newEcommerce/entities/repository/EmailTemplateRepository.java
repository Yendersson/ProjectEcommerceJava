package ar.com.ecommerce.newEcommerce.entities.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.com.ecommerce.newEcommerce.entities.EmailTemplate;

@Repository
public interface EmailTemplateRepository extends CrudRepository<EmailTemplate, Long>{

	@Query("FROM EmailTemplate e WHERE e.active = true AND code = :code")
	public EmailTemplate findTemplateByCode(@Param("code") String code);
	
}
