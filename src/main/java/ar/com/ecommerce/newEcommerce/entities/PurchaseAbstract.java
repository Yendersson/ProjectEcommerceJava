package ar.com.ecommerce.newEcommerce.entities;

import java.util.Collection;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class PurchaseAbstract {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String cod;
	
	@ManyToOne
	@JoinColumn(name = "user", referencedColumnName = "id")
	private User user;
	
	private String date;
	
	@ManyToMany
	@JoinTable(name = "purchase_products",
	      joinColumns = @JoinColumn(name = "purchase"),
	      inverseJoinColumns = @JoinColumn(name = "products"))
	private Collection<Product> products;
	
	private Double amount;
	
	private Double amountIva;
	
	private Double amountTotal;
	
	private String purchaseId;
	
	private String status;
}