package ar.com.ecommerce.newEcommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import ar.com.ecommerce.newEcommerce.filter.FilterPage;
import ar.com.ecommerce.newEcommerce.servlets.MpPayment;

@Configuration
public class AppConfig {
	
	@Bean
	public FilterRegistrationBean<FilterPage> loggingFilter() {
        FilterRegistrationBean<FilterPage> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new FilterPage());
        //registrationBean.addUrlPatterns("/admin/*"); // Define las URL a las que se aplicará el filtro
        registrationBean.addUrlPatterns("/api/purchase/*"); 
        registrationBean.addUrlPatterns("/api/user/*"); 
        registrationBean.addUrlPatterns("/MpPayment"); 
        return registrationBean;
    }
}
