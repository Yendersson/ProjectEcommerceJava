package ar.com.ecommerce.newEcommerce.entities;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Table(name = "product")
@Entity
public class Product extends ProductAbstract{

	@Override
	public String getCssStyle() {
		System.out.println(getStock());
		if (getStock() != null && getStock() < 100) return "text-decoration: underline";
		return super.getCssStyle();
	}
	
}

