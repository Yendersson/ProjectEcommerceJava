package ar.com.ecommerce.newEcommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import ar.com.ecommerce.newEcommerce.entities.Product;
import ar.com.ecommerce.newEcommerce.utils.CmdProductXML;
import ar.com.ecommerce.newEcommerce.utils.ServicesXML;

//@SpringBootApplication
public class CmdXml{
	
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(CmdXml.class);
		app.setWebApplicationType(WebApplicationType.NONE);
		app.run(args);
		//cmd.getProduct();
	}

}
