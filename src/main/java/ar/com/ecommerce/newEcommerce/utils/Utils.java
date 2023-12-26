package ar.com.ecommerce.newEcommerce.utils;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class Utils {
	
	 
	
	public static String saveFile(MultipartFile upload) {
		String pathFiles = "/home/yender/education-it-workspace/newEcommerce/files/images";
		File uploads = new File(pathFiles);
		
		String pathAbsolute ="";
		
		try {
			String filename = upload.getOriginalFilename().toString();
			InputStream input = upload.getInputStream();
			
			if (input != null) {
				File file = new File(uploads, filename);
				pathAbsolute = file.getName();
				Files.copy(input, file.toPath());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pathAbsolute;
		
	}
}
