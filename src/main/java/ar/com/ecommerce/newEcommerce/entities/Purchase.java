package ar.com.ecommerce.newEcommerce.entities;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "purchase")
@Entity
public class Purchase extends PurchaseAbstract{
	
	@Override
	public String getDate() {
		DateTimeFormatter formatoDeseado = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:ss");
		OffsetDateTime fecha = OffsetDateTime.parse(super.getDate());
		
		return fecha.format(formatoDeseado);
	}
	
	@Override
	public String getCssStyle() {
		if(getStatus().equals("approved")) return "color:green";
		return super.getCssStyle();
	}
	
	@Override
	public String getCssClass() {
		if(getStatus().equals("approved")) return "approved";
		return super.getCssClass();
	}
}
