package ar.com.ecommerce.newEcommerce.controller.admin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ar.com.ecommerce.newEcommerce.entities.PaymentMethods;
import ar.com.ecommerce.newEcommerce.entities.repository.PaymentMethodRepository;
import ar.com.ecommerce.newEcommerce.utils.Utils;

@Controller
public class PaymenMehtodAdminController {
	
	@Autowired
	private PaymentMethodRepository repo;
	
	public PaymenMehtodAdminController(PaymentMethodRepository repo) {
		this.repo = repo;
	}
	
	@PostMapping("/admin/payment")
	public String postPaymentMethods(PaymentMethods cat, @RequestParam("img") MultipartFile file) {
		System.out.println("PAYMETN POST");
		cat.setImage(Utils.saveFile(file));
		System.out.println(cat);
		repo.save(cat);
		
		return "redirect:/admin/payment";
	}
	
	 @GetMapping("/admin/payment")
		public String paymentListAdmin(Model model) {
			 
			model.addAttribute("payment", (Collection<PaymentMethods>) repo.findAll());
			return "payment_methods_list";
		}
	 
	 @GetMapping("/admin/payment/{id}")
	 public String paymentAdmin(@PathVariable Long id, Model model) {
		PaymentMethods cat = new PaymentMethods();
		if (id != 0) cat = repo.findById(id).get();
		model.addAttribute("payment", cat);
		return "payment_methods";
	}
	 
		@GetMapping("/admin/payment/delete/{id}")
		public String deletePaymentMethods(@PathVariable Long id) {
			PaymentMethods p = repo.findById(id).get(); 
			repo.delete(p);
			
			return "redirect:/admin/payment";
		}

}
