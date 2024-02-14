package ar.com.ecommerce.newEcommerce.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ar.com.ecommerce.newEcommerce.entities.Email;
import ar.com.ecommerce.newEcommerce.entities.EmailTemplate;
import ar.com.ecommerce.newEcommerce.entities.Product;
import ar.com.ecommerce.newEcommerce.entities.Purchase;
import ar.com.ecommerce.newEcommerce.entities.PurchaseProduct;
import ar.com.ecommerce.newEcommerce.entities.User;
import ar.com.ecommerce.newEcommerce.entities.repository.FilterEntity;
import ar.com.ecommerce.newEcommerce.entities.repository.ProductRepository;
import ar.com.ecommerce.newEcommerce.entities.repository.UserRepository;
import ar.com.ecommerce.newEcommerce.utils.Utils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Service
public class PurchaseServices extends PurchaseServicesAbstract{
	
	@Autowired
	private ProductRepository repoProduct;
	@Autowired
	private UserRepository repoUser;
	@Autowired
	private EmailServices service;
	@Autowired
	private EmailTemplateServices serviceTemplate;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public PurchaseServices() {}

	//Store by json
	public Purchase store(String json) {
		Purchase p = buildPurchase(json);
		buildTemplate(p);
		return store(p);
	}
	
	public Collection<Purchase> filter(Map<String, String> params){
		FilterEntity<Purchase> purchaseFilter = new FilterEntity<>(entityManager);
		Collection<Purchase> purchases = purchaseFilter.itemsFiltered(params, Purchase.class.getSimpleName());
		System.out.println(purchases);
		return purchases; 
	}
	
	public Purchase getOnePurchase(Long id) {
		Purchase p = new Purchase();
		if (id != 0) p = repo.findById(id).get();
		return p;
	}
	
	public Collection<Purchase> getAllPurchases(Map<String, String> params){
		if (params.isEmpty() || (params.size() < 2 && params.get("page") != null)) {
			if (params.get("page") == null || "1".equals(params.get("page"))) return repo.findAllOrderByDate(0); 
			repo.count();
			return repo.findAllOrderByDate(Utils.pagination(params.get("page")));
			
		} 
		return filter(params);
	}
	
	//Delete by ID
	public void delete(Long id) {
		Purchase p = repo.findById(id).get();
		delete(p);
	}
	
	//build json to object
	public Purchase buildPurchase(String jsonPurchase){
		ObjectMapper convertJson = new ObjectMapper();
		JsonNode json;
		Purchase p = new Purchase();
		
		try {
			json = convertJson.readTree(jsonPurchase);
			
			User user = repoUser.findById(1L).get();
			
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
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} finally {
			return p;
		}
	}
	
	//Sending template purchase email
	public void buildTemplate(Purchase p) {
		
		EmailTemplate template = null;
		Email email = new Email();
		email.setHtml(false);
		
		if (p.getStatus().equals("approved")) {
			
			template = serviceTemplate.findTemplate("PA");
			
			if (template.getIsHtml()) email.setHtml(true);
			
			email.setSubject(template.getSubject().replaceAll("COD_ID", p.getPurchaseId()));
			email.setRecipients(p.getUser().getEmail());
			String productos = "";
			
			for (PurchaseProduct pr : p.getPurchaseProduct()) {
				productos += pr.getProduct().getTitle() + "\n";
			}
			email.setMessage(template.getBody().replaceAll("PRODUCTS", productos));
			email.setSend(true);
		}
		service.buildEmail(email);
	}
}
