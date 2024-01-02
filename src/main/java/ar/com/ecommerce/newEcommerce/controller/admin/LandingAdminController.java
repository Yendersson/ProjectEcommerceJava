package ar.com.ecommerce.newEcommerce.controller.admin;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ar.com.ecommerce.newEcommerce.entities.Banner;
import ar.com.ecommerce.newEcommerce.entities.Category;
import ar.com.ecommerce.newEcommerce.entities.Icon;
import ar.com.ecommerce.newEcommerce.entities.Landing;
import ar.com.ecommerce.newEcommerce.entities.repository.BannerRepository;
import ar.com.ecommerce.newEcommerce.entities.repository.IconRepository;
import ar.com.ecommerce.newEcommerce.entities.repository.LandingRepository;

@Controller
public class LandingAdminController {
	
	@Autowired
	private LandingRepository repo;
	private BannerRepository repoBanner;
	private IconRepository repoIcon;
	
	public LandingAdminController(LandingRepository repo, BannerRepository repoBanner, IconRepository repoIcon ) {
		this.repo = repo;
		this.repoBanner = repoBanner;
		this.repoIcon = repoIcon;
	}
	
	@PostMapping("/admin/landing")
	public String postSubCategory(Landing landing) {
		repo.save(landing);
		
		return "redirect:/admin/landing";
	}
	
	 @GetMapping("/admin/landing/{id}")
	public String subCategoryAdmin(@PathVariable Long id, Model model) {
		 Landing landing = new Landing();
		 if (id != 0) landing = repo.findById(id).get();
		 
		model.addAttribute("banners", (Collection<Banner>) repoBanner.findAll());
		model.addAttribute("icons", (Collection<Icon>) repoIcon.findAll());
	 	model.addAttribute("landing", landing);
		return "landing";
	}
	 
	 @GetMapping("/admin/landing")
	public String subCcategoryListAdmin(Model model) {
		 
		model.addAttribute("landing", (Collection<Landing>) repo.findAll());
		return "landing_list";
	}
	 
		@GetMapping("/admin/landing/delete/{id}")
		public String deleteProduct(@PathVariable Long id) {
			Landing p = repo.findById(id).get(); 
			repo.delete(p);
			
			return "redirect:/admin/landing";
		}
	
	
}
