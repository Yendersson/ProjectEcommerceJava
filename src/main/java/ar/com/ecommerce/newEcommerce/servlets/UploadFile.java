package ar.com.ecommerce.newEcommerce.servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class UploadFile extends HttpServlet{
	String pathFiles = "/home/yender/education-it-workspace/newEcommerce/files/imgs"; 
	File uploads = new File(pathFiles);

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		super.service(req, resp);
	}
	
	private void saveCostumer(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String name = req.getParameter("name");
			Part part = (req.getPart("file"));
			
			if (part == null) {
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private String saveFile(Part part, File pathUpload) {
		String pathAbsolute ="";
		
		try {
			
			Path path = Paths.get(part.getSubmittedFileName());
			String filename = path.getFileName().toString();
			InputStream input = part.getInputStream();
			
			if (input != null) {
				File file = new File(pathUpload, filename);
				pathAbsolute = file.getAbsolutePath();
				Files.copy(input, file.toPath());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pathAbsolute;
		
	}
}
