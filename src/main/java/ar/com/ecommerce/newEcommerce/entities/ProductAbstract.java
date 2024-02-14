package ar.com.ecommerce.newEcommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class ProductAbstract extends GlobalProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String cod;
	
	private String title;
	
	private String subtitle;
	
	private String textSummary;
	
	@Column(columnDefinition = "LONGTEXT")
	private String description;
	
	private Double iva_price;
	
	private Double unit_price;
	
	private String info;
	
	private Double current_price;
	
	private String picture;
	
	private String date;
	
	private Integer stock;
	
	@JoinColumn(name = "category", referencedColumnName = "id")
	@ManyToOne
	private Category category;
	
	@JoinColumn(name = "subcategory", referencedColumnName = "id")
	@ManyToOne
	private Subcategory subCategory;
	
	
	/*@ManyToMany(mappedBy = "products")
	private Collection<Purchase> purchases;*/
	

}
