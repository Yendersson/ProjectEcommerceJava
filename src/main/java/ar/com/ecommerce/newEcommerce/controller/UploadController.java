package ar.com.ecommerce.newEcommerce.controller;

import java.io.IOException;
import java.nio.file.Files;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UploadController {
	
    @GetMapping("/update/{img}")
    public ResponseEntity<byte[]> getImage(@PathVariable String img) throws IOException {
    	
    	System.out.println("HEARE");
        String imagePath = "/home/yender/education-it-workspace/newEcommerce/files/images/" + img;
        System.out.println(img);
        Resource resource = new FileSystemResource(imagePath);

        // Verifica si el recurso existe
        if (resource.exists()) {
        	System.out.println("EXist");
            byte[] imageBytes = Files.readAllBytes(resource.getFile().toPath());

            // Devuelve la imagen como bytes con el tipo de contenido adecuado
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageBytes);
        } else {
        	
        	System.out.println("DONT EXist");
            // Maneja si la imagen no existe
            return ResponseEntity.notFound().build();
        }
    }
}
