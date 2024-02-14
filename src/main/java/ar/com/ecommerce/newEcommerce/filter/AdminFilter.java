package ar.com.ecommerce.newEcommerce.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AdminFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			HttpSession session = req.getSession();
			
			System.out.println("SESSION: "+ session.getAttribute("user"));
			if (session.getAttribute("user") == null) {
				req.getRequestDispatcher("/login").forward(request, response);
			} else {
				chain.doFilter(request, response);
			}
 			
			
		
	}
	

}
