package ar.com.ecommerce.newEcommerce.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.ecommerce.newEcommerce.utils.jwt;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FilterPage implements Filter{
	
	private final Logger log = LoggerFactory.getLogger(String.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		 HttpServletResponse resp = (HttpServletResponse) response;
	        resp.setHeader("Access-Control-Allow-Origin", "*"); // Reemplaza con el dominio de tu aplicación React
	        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
	        resp.setHeader("Access-Control-Allow-Headers", "*");
	        
	        System.out.println(((HttpServletRequest)request).getHeader("Authorization"));
	        
	        if (((HttpServletRequest)request).getHeader("Authorization") != null && jwt.evaluateToken(((HttpServletRequest)request).getHeader("Authorization").substring(7))) {
	        	chain.doFilter(request, response);
	        	
	        } else {
	        	((HttpServletRequest)request).getRequestDispatcher("/api/authorization").forward(request, response);
	 
	        }
		
		
		
	}

}
