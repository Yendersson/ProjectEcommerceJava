package ar.com.ecommerce.newEcommerce.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class EmailTemplateAbstract extends GlobalProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private Boolean isHtml;
	
	private String subject;
	
	@Column(columnDefinition = "LONGTEXT")
	private String body;
	
	private String code;
	
	private Boolean active;
	
	private String recipients;
	
	
}
