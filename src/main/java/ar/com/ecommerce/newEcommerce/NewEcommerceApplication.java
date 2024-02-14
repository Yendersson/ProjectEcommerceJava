package ar.com.ecommerce.newEcommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import ar.com.ecommerce.newEcommerce.entities.repository.ProductRepository;

@SpringBootApplication
@ServletComponentScan(basePackages = "ar.com.ecommerce.newEcommerce.servlets") 
public class NewEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewEcommerceApplication.class, args);
		
	}

}
