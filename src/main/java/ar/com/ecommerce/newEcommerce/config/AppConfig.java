package ar.com.ecommerce.newEcommerce.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ar.com.ecommerce.newEcommerce.filter.FilterPage;

@Configuration
public class AppConfig {
	
	@Bean
	public FilterRegistrationBean<FilterPage> loggingFilter() {
        FilterRegistrationBean<FilterPage> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new FilterPage());
        registrationBean.addUrlPatterns("/admin/*"); // Define las URL a las que se aplicará el filtro
        return registrationBean;
    }
}
