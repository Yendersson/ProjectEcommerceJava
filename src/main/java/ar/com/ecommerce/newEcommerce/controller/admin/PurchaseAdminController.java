package ar.com.ecommerce.newEcommerce.controller.admin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ar.com.ecommerce.newEcommerce.entities.Purchase;
import ar.com.ecommerce.newEcommerce.entities.repository.PurchaseRepository;

@Controller
public class PurchaseAdminController {
	
	@Autowired
	private PurchaseRepository repo;
	
	public PurchaseAdminController(PurchaseRepository repo) {
		this.repo = repo;
	}
	
	@GetMapping("/admin/purchase")
	public String listPurchase(org.springframework.ui.Model model) {
		model.addAttribute("Purchases", (Collection<Purchase>) repo.findAllOrderByDate());
		return "purchase_list";
	}
	
	@GetMapping("/admin/purchase/{id}")
	public String listPurchase(@PathVariable Long id, org.springframework.ui.Model model) {
		Purchase Purchase = new Purchase();
		if (id != 0) Purchase = repo.findById(id).get();

		model.addAttribute("purchase", Purchase);
		return "purchase";
	}
	
	@PostMapping("/admin/purchase")
	public String postPurchase(Purchase Purchase) {
		repo.save(Purchase);
		return  "redirect:/admin/purchase";
	}
	
	@GetMapping("/admin/purchase/delete/{id}")
	public String deletePurchase(@PathVariable Long id) {
		Purchase p = repo.findById(id).get(); 
		repo.delete(p);
		
		return "redirect:/admin/purchase";
	}
	
}
