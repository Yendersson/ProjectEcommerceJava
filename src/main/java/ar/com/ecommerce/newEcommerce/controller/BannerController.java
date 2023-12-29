package ar.com.ecommerce.newEcommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ecommerce.newEcommerce.entities.BannerImage;
import ar.com.ecommerce.newEcommerce.entities.repository.BannerImageRepository;
import ar.com.ecommerce.newEcommerce.entities.repository.BannerRepository;

@RestController
public class BannerController {
	
	@Autowired
	private BannerRepository repo;
	
	@Autowired
	private BannerImageRepository repoImage;
	
	public BannerController(BannerRepository repo, BannerImageRepository repoImage) {
		this.repo = repo;
		this.repoImage = repoImage;
	}

}
