package ar.com.ecommerce.newEcommerce.entities.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.com.ecommerce.newEcommerce.entities.BannerImage;

@Repository
public interface BannerImageRepository extends CrudRepository<BannerImage, Long>{

}
