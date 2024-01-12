package ar.com.ecommerce.newEcommerce.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@lombok.Data
@MappedSuperclass
public class EmailAbstract extends GlobalProperties{
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String recipients;
	
	private String subject;
	
	@Column(columnDefinition = "LONGTEXT")
	private String message;
	
	private Boolean send;
	
	private Boolean reader;
	
	private Boolean html;
	
}
