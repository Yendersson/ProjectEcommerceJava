package ar.com.ecommerce.newEcommerce.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ar.com.ecommerce.newEcommerce.entities.Product;
import ar.com.ecommerce.newEcommerce.entities.Purchase;
import ar.com.ecommerce.newEcommerce.entities.PurchaseProduct;
import ar.com.ecommerce.newEcommerce.entities.User;
import ar.com.ecommerce.newEcommerce.entities.repository.ProductRepository;
import ar.com.ecommerce.newEcommerce.entities.repository.PurchaseRepository;
import ar.com.ecommerce.newEcommerce.entities.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class PurchaseServices {
	
	@Autowired
	private PurchaseRepository repo;
	@Autowired
	private ProductRepository repoProduct;
	@Autowired
	private UserRepository repoUser;
	
	public PurchaseServices() {}
	
	public PurchaseServices(PurchaseRepository repo, ProductRepository repoProduct, UserRepository repoUser) {
		this.repo = repo;
		this.repoProduct = repoProduct;
		this.repoUser = repoUser;
	}
	
	@Transactional
	public void buildPurchase(String jsonPurchase){
		ObjectMapper convertJson = new ObjectMapper();
		JsonNode json;
		
		try {
			json = convertJson.readTree(jsonPurchase);
			
			User user = repoUser.findById(1L).get();
			
			Purchase p = new Purchase();
			p.setPurchaseProduct(new ArrayList<>());
			p.setPurchaseId(json.get("id").asText());
			p.setAmountTotal(json.get("transaction_amount").asDouble());
			p.setAmount(json.get("transaction_amount").asDouble());
			p.setAmountIva(0.0);
			p.setDate(json.get("date_created").asText());
			p.setInstallments(json.get("installments").asInt());
			p.setPaymentMethodId(json.get("payment_method_id").asText());
			p.setPaymentTypeId(json.get("payment_type_id").asText());
			p.setStatus(json.get("status").asText());
			p.setStatusDetailed(json.get("status_detail").asText());
			p.setUser(user);
			
			if (json.get("additional_info").get("items").isArray()) {
				
				for (JsonNode jsonIndex : json.get("additional_info").get("items")) {
						Product product = repoProduct.findById(jsonIndex.get("id").asLong()).get();
						if (product.getStock() < jsonIndex.get("quantity").asInt()) break;
						
						if (p.getStatus().equals("approved")) {
							product.setStock(product.getStock() - jsonIndex.get("quantity").asInt());
							repoProduct.save(product);
						};
						
						PurchaseProduct purchaseProduct = new PurchaseProduct();
						purchaseProduct.setProduct(product);
						purchaseProduct.setQuantity(jsonIndex.get("quantity").asInt());
						p.getPurchaseProduct().add(purchaseProduct);
				}
			}
			repo.save(p);
			
		} catch (JsonMappingException e) {
			
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
