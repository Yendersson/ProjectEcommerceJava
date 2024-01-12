package ar.com.ecommerce.newEcommerce.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;

@MappedSuperclass
public abstract class GlobalProperties {
	
	public GlobalProperties() {
		this.updated_at = LocalDateTime.now().toString(); 
	}
	
	@Transient
	@JsonIgnore
	private String cssStyle;
	
	@Transient
	@JsonIgnore
	private String cssClass;
	
	private String updated_at;

	public String getCssStyle() {
		return cssStyle;
	}

	public void setCssStyle(String cssStyle) {
		this.cssStyle = cssStyle;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	
	
	
	
	
}
