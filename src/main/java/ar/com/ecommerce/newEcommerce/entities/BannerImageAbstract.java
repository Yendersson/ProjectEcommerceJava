package ar.com.ecommerce.newEcommerce.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@lombok.Data
@MappedSuperclass
public class BannerImageAbstract extends GlobalProperties{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String url;
}
