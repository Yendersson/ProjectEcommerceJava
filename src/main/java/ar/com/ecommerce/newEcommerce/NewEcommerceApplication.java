package ar.com.ecommerce.newEcommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan(basePackages = "ar.com.ecommerce.newEcommerce.servlets") 
public class NewEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewEcommerceApplication.class, args);
	}

}
