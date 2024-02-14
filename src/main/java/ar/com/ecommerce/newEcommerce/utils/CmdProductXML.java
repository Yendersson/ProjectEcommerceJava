package ar.com.ecommerce.newEcommerce.utils;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ar.com.ecommerce.newEcommerce.entities.Category;
import ar.com.ecommerce.newEcommerce.entities.Product;
import ar.com.ecommerce.newEcommerce.entities.Subcategory;
import ar.com.ecommerce.newEcommerce.entities.repository.ProductRepository;

@Component
public class CmdProductXML implements CommandLineRunner{
	
	@Autowired
	private ServicesXML service;
	
	public String msgCmd() {
		return "HOLA DESDE EL ELEMENTO";
	}
	
	public void importProductXml() {
		Product p = null;
		Category c = null;
		Subcategory sub = null;
		File file = new File("fb_catalog.xml");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			//ANALIZA EL DOCUMENTO
			Document document = db.parse(file);
			
			
			NodeList items = document.getElementsByTagName("item");
			
			for (int i = 0; i < items.getLength(); i++) {
				Node nNode = items.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					
					
					Element element = (Element) nNode;
					p = service.findByCode(getText(element, "id"));
					c = service.findById(6L);
					sub = service.findByTitle(getText(element, "product_type"));
					//c = service.findByTitle(getText(element, "product_type"));
					
					if (p == null) {
						p = new Product();
					}
					
					if (sub == null) sub = new Subcategory();
					
					/*if (c == null) {
						c = new Category();
					}*/
					sub.setCategory(c);
					sub.setTitle(getText(element, "product_type"));
					//c.setTitle(getText(element, "product_type"));
					p.setSubCategory(sub);
					p.setCategory(c);
					p.setCod(getText(element, "id"));
					p.setTitle(getText(element, "title"));
					p.setCurrent_price(Double.parseDouble(getText(element, "price").replaceAll("ARS", "").trim()) );
					p.setDescription(getText(element, "description"));
					p.setPicture(getText(element, "image_link"));
					
				}
				//service.save(c);
				service.save(sub);
				service.save(p);
				
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public static String getText(Element element, String attr) {
		
		NodeList nList = element.getElementsByTagName(attr);
		
		if (nList.getLength() > 0) {
			return nList.item(0).getTextContent();
			
		}
		return null;
	}

	@Override
	public void run(String... args) throws Exception {
		
		//importProductXml();
	}

	
}


