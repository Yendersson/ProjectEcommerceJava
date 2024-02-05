package ar.com.ecommerce.newEcommerce.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class Utils {
	
	 public static String projectPath() {
		 String path = null;
		 try {
			Process process = Runtime.getRuntime().exec("pwd");
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = null;
			StringBuilder out = new StringBuilder();
			while((line = reader.readLine()) != null) {
				out.append(line);
			}
			path = out.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return path;
	 }
	
	public static String saveFile(MultipartFile upload) {
		String pathFiles = projectPath()+"/files/images";
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
	
	public static int pagination(String num) {
		return (Integer.parseInt(num) -1)*20;  
	}
}
