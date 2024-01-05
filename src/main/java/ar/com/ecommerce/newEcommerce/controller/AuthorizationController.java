package ar.com.ecommerce.newEcommerce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ecommerce.newEcommerce.entities.Data;

@RestController
public class AuthorizationController {
	
	@GetMapping("api/authorization")
	public Data unahotorizade() {
		Data data = new Data();
		data.setData("NO");
		data.setError(true);
		data.setMessage("Debes autenticarte");
		return data;
	}

}
