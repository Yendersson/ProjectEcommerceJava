package ar.com.ecommerce.newEcommerce.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ar.com.ecommerce.newEcommerce.entities.Banner;
import ar.com.ecommerce.newEcommerce.entities.BannerImage;
import ar.com.ecommerce.newEcommerce.entities.repository.BannerImageRepository;
import ar.com.ecommerce.newEcommerce.entities.repository.BannerRepository;
import ar.com.ecommerce.newEcommerce.utils.Utils;

@Controller
public class BannerAdminController {
	
	@Autowired
	private BannerRepository repo;
	
	@Autowired
	private BannerImageRepository repoImage;

	/*public BannerAdminController(BannerRepository repo, BannerImageRepository repoImage) {
		this.repo = repo;
		this.repoImage = repoImage;
	}*/
	
	@GetMapping("/admin/banner")
	public String getBanners(Model model){
		List<Banner> banners = (List<Banner>) repo.findAll();
		model.addAttribute("banner", banners);
		return "banner_list";
	}
	
	@GetMapping("/admin/banner/{id}")
	public String getBanners(@PathVariable Long id, Model model){
		Banner b = repo.findById(id).get();
		model.addAttribute("banner", b);
		model.addAttribute("bannerImages", b.getImages());
		return "banner";
	}
	
	@PostMapping("/admin/banner")
	public String postBanners(Banner banner, @RequestParam("files") List<MultipartFile> files){

		for (MultipartFile file : files) {
			if (file.getSize() == 0) continue;
			if (banner.getImages() == null) banner.setImages(new ArrayList<>());
			BannerImage bannerImg = new BannerImage(); 
			bannerImg.setUrl(Utils.saveFile(file));
			banner.getImages().add(bannerImg);
		}
		repo.save(banner);

		return "redirect:/admin/banner";
	}
	
	@GetMapping("/admin/banner/delete/{id}")
	public String deleteBanners(@PathVariable Long id) {
		Banner b = repo.findById(id).get();
		repo.delete(b);
		
		return "redirect:/admin/banner";
	}
	
	@GetMapping("/admin/bannerImage/delete/{id}")
	public String deleteBannerImg(@PathVariable Long id) {
		BannerImage b = repoImage.findById(id).get();
		repoImage.delete(b);

		return "redirect:/admin/banner";
	}
	
	
	
}
