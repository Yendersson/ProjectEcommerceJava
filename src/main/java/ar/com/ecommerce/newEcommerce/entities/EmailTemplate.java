package ar.com.ecommerce.newEcommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "email_template")
public class EmailTemplate extends EmailTemplateAbstract{
	
	@Override
	public String getCssClass() {
		if (!getActive()) return "no-active";
		return super.getCssClass();
	}
	
	@Override
	public String getCssStyle() {
		if (!getActive()) return "text-decoration: line-through; color: #eee;"; 
		return super.getCssStyle();
	}
}
