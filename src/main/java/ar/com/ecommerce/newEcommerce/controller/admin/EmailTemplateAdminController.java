package ar.com.ecommerce.newEcommerce.controller.admin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ar.com.ecommerce.newEcommerce.entities.EmailTemplate;
import ar.com.ecommerce.newEcommerce.entities.repository.EmailTemplateRepository;

@Controller
public class EmailTemplateAdminController {
	
	@Autowired
	private EmailTemplateRepository repo;
	
	public EmailTemplateAdminController(EmailTemplateRepository repo) {
		this.repo = repo;
	}
	@PostMapping("/admin/emailTemplate")
	public String postEmailTemplate(EmailTemplate emailTemplate) {
		
		repo.save(emailTemplate);
		
		return "redirect:/admin/emailTemplate";
	}
	
	 @GetMapping("/admin/emailTemplate/{id}")
	public String emailTemplateAdmin(@PathVariable Long id, Model model) {
		 EmailTemplate emailTemplate = new EmailTemplate();
		 if (id != 0) emailTemplate = repo.findById(id).get();
		 
	 	model.addAttribute("emailTemplate", emailTemplate);
		return "email_template";
	}
	 
	 @GetMapping("/admin/emailTemplate")
	public String emailTemplateListAdmin(Model model) {
		 
		model.addAttribute("emailTemplates", (Collection<EmailTemplate>) repo.findAll());
		return "email_template_list";
	}
	 
		@GetMapping("/admin/emailTemplate/delete/{id}")
		public String deleteTemplate(@PathVariable Long id) {
			EmailTemplate p = repo.findById(id).get(); 
			repo.delete(p);
			
			return "redirect:/admin/email_template";
		}

}
