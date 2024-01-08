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


@Entity
@Table(name = "landing")
public class Landing extends LandingAbstract{
	
	@Override
	public String getCssClass() {
		if(isActive()) return "approved";
		return super.getCssClass();
	}
}
