package ar.com.ecommerce.newEcommerce.entities;

import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String cod;
	
	private String title;
	
	private String subtitle;
	
	private String textSummary;
	
	private String description;
	
	private Double iva_price;
	
	private Double unit_price;
	
	private String info;
	
	private Double current_price;
	
	private String picture;
	
	private String date;
	
	@JoinColumn(name = "category", referencedColumnName = "id")
	@ManyToOne
	private Category category;
	
	@JoinColumn(name = "subcategory", referencedColumnName = "id")
	@ManyToOne
	private Subcategory subCategory;
	
	
	/*@ManyToMany(mappedBy = "products")
	private Collection<Purchase> purchases;*/
}
