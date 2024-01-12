package ar.com.ecommerce.newEcommerce.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ar.com.ecommerce.newEcommerce.entities.Banner;
import ar.com.ecommerce.newEcommerce.entities.repository.BannerRepository;
import ar.com.ecommerce.newEcommerce.utils.Utils;

@Controller
public class UploadController {
	
    @GetMapping("/update/{img}")
    public ResponseEntity<byte[]> getImage(@PathVariable String img) throws IOException {
    	
    	System.out.println("HEARE");
        String imagePath = Utils.projectPath()+"/files/images/" + img;
        System.out.println(img);
        Resource resource = new FileSystemResource(imagePath);
       
        // Verifica si el recurso existe
        if (resource.exists()) {
            byte[] imageBytes = Files.readAllBytes(resource.getFile().toPath());
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageBytes);
        } else {
            // Maneja si la imagen no existe
            return ResponseEntity.notFound().build();
        }
    }
}
