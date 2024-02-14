package ar.com.ecommerce.newEcommerce.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ecommerce.newEcommerce.services.ExportProductsService;
import ar.com.ecommerce.newEcommerce.services.ExportPurchaseService;

@RestController
public class ExportEntity {
	
	@Autowired
	ExportPurchaseService purchase;
	@Autowired
	ExportProductsService product;
	
	@GetMapping("/admin/purchaseExcel")
	public ResponseEntity<byte[]> ExportPurchasesExcel() {
		
		byte[] excels = purchase.createXLSFile();
		
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "purchase.xlsx");
        
		return new ResponseEntity<>(excels, headers, HttpStatus.OK);
	}
	
	@GetMapping("/admin/productExcel")
	public ResponseEntity<byte[]> ExportProductExcel() {
		
		byte[] excels = product.createXLSFile();
		
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "purchase.xlsx");
        
		return new ResponseEntity<>(excels, headers, HttpStatus.OK);
	}

}
