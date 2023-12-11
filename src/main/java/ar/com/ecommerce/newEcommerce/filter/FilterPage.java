package ar.com.ecommerce.newEcommerce.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class FilterPage implements Filter{
	
	private final Logger log = LoggerFactory.getLogger(String.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		log.info("Intercepcion en el filter");
		
		chain.doFilter(request, response);
		
	}

}
