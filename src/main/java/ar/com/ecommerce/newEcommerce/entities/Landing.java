package ar.com.ecommerce.newEcommerce.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "landing")
public class Landing {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private boolean active;
	
	private boolean showOngs;
	
	private boolean showPayments;
	
	@JoinColumn(name = "banner", referencedColumnName = "id")
	@ManyToOne
	private Banner banner;
	
	@JoinColumn(name = "icon", referencedColumnName = "id")
	@ManyToOne
	private Icon icon;
	
	@Transient
	private List<Ong> ongs;
	
	@Transient
	private List<PaymentMethods> payments;
	
}
