package ar.com.ecommerce.newEcommerce.controller.admin;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.com.ecommerce.newEcommerce.entities.Purchase;
import ar.com.ecommerce.newEcommerce.entities.repository.PurchaseRepository;
import ar.com.ecommerce.newEcommerce.services.PurchaseServices;

@Controller
public class PurchaseAdminController {

	@Autowired
	private PurchaseRepository repo;

	@Autowired
	private PurchaseServices service;

	public PurchaseAdminController(PurchaseRepository repo) {
		this.repo = repo;
	}

	@GetMapping("/admin/purchase")
	public String listPurchase(org.springframework.ui.Model model, @RequestParam Map<String, String> params) {
		model.addAttribute("Purchases", (Collection<Purchase>) service.getAllPurchases(params));
		model.addAttribute("page", params.get("page") != null? params.get("page"): 1);
		return "purchase_list";
	}

	@GetMapping("/admin/purchase/{id}")
	public String listPurchase(@PathVariable Long id, org.springframework.ui.Model model) {
		model.addAttribute("purchase", service.getOnePurchase(id));
		return "purchase";
	}

	@PostMapping("/admin/purchase")
	public String postPurchase(Purchase Purchase) {
		repo.save(Purchase);
		return "redirect:/admin/purchase";
	}

	@GetMapping("/admin/purchase/delete/{id}")
	public String deletePurchase(@PathVariable Long id) {
		service.delete(id);
		return "redirect:/admin/purchase";
	}

}
