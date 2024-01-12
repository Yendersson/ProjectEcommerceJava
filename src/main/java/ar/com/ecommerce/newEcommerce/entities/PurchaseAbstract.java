package ar.com.ecommerce.newEcommerce.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class PurchaseAbstract extends GlobalProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String cod;
	
	private String purchaseId;
	
	@ManyToOne
	@JoinColumn(name = "user", referencedColumnName = "id")
	private User user;
	
	private String date;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "purchase_product")
	private List<PurchaseProduct> purchaseProduct;
	
	private Integer installments;
	
	private String paymentMethodId;
	
	private String paymentTypeId;
	
	private Double amount;
	
	private Double amountIva;
	
	private Double amountTotal;
	
	private String status;
	
	private String statusDetailed;	
}
