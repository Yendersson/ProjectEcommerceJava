package ar.com.ecommerce.newEcommerce.entities;

import java.util.Collection;

import ar.com.ecommerce.newEcommerce.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Table(name = "user")
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String role;
	
	private String firstName;
	
	private String lastName;
	
	private String username;
	
	private String phone;
	
	private String password;
	
	private String email;
	
	private String date;
	
	private String lastLogin;
	
	public User() {
		this.role = Role.USER.toString();
	}
}
