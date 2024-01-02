package ar.com.ecommerce.newEcommerce.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ar.com.ecommerce.newEcommerce.entities.Icon;
import ar.com.ecommerce.newEcommerce.entities.Icons;
import ar.com.ecommerce.newEcommerce.entities.repository.IconRepository;
import ar.com.ecommerce.newEcommerce.entities.repository.IconsRepository;
import ar.com.ecommerce.newEcommerce.utils.Utils;

@Controller
public class IconAdminController {
	
	@Autowired 
	private IconRepository repo;
	
	@Autowired 
	private IconsRepository repoIcons;
	
	public IconAdminController(IconRepository repo, IconsRepository repoIcons) {
		this.repo = repo;
		this.repoIcons = repoIcons;
	}
	
	@GetMapping("/admin/icon")
	public String getIcons(Model model){
		List<Icon> icons = (List<Icon>) repo.findAll();
		model.addAttribute("icon", icons);
		return "icon_list";
	}
	
	@GetMapping("/admin/icon/{id}")
	public String getIcons(@PathVariable Long id, Model model){
		Icon b = new Icon();
		if (id != 0) b = repo.findById(id).get();
		
		model.addAttribute("icon", b);
		model.addAttribute("icons", b.getIcons());
		return "icon";
	}
	
	@PostMapping("/admin/icon")
	public String postIcons(Icon icon, @RequestParam("files") List<MultipartFile> files){

		for (MultipartFile file : files) {
			if (file.getSize() == 0) continue;
			Icons iconImg = new Icons(); 
			iconImg.setImage(Utils.saveFile(file));
			icon.getIcons().add(iconImg);
		}
		repo.save(icon);

		return "redirect:/admin/icon";
	}
	
	@GetMapping("/admin/icon/delete/{id}")
	public String deleteIcons(@PathVariable Long id) {
		Icon b = repo.findById(id).get();
		repo.delete(b);
		
		return "redirect:/admin/icon";
	}
	
	@GetMapping("/admin/icons/delete/{id}")
	public String deleteIconImg(@PathVariable Long id) {
		Icons b = repoIcons.findById(id).get();
		repoIcons.delete(b);

		return "redirect:/admin/icon";
	}
	
}
