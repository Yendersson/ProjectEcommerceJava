package ar.com.ecommerce.newEcommerce.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class CategoryAbstract extends GlobalProperties{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String picture;
	
	private String title;

	/*@JoinColumn(name = "subactegory", referencedColumnName = "id")
	@ManyToOne
	private Subcategory subcategory;*/
}
