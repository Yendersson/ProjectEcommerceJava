package ar.com.ecommerce.newEcommerce.imports;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import ar.com.ecommerce.newEcommerce.JDBC.Connection;
import ar.com.ecommerce.newEcommerce.utils.Utils;

public class PurchaseExcel {
	public static void main(String[] args) {
		PurchaseExcel purchaseOut = new PurchaseExcel();
		
		System.out.println(System.getProperties());
		//purchaseOut.exportPurchases();
	}
	
	private Workbook workbout;
	private Sheet sheet;
	private LocalDate date;
	
	public PurchaseExcel() {
		this.date = LocalDate.now();
		this.workbout = new HSSFWorkbook();
		sheet = workbout.createSheet("Hoja1");
	}
	
	public void exportPurchases() {
		Connection conn = new Connection();
		List<Object[]> resultados = new ArrayList<>();
		
		try {
			conn.ps = conn.connect.prepareStatement("SELECT * FROM purchase WHERE  ORDER BY date DESC");
			conn.rs = conn.ps.executeQuery();
			Object[] titles = {"amount", "amount_iva", "amount_total", "cod", "date", "purchase_id", "status", "user", "status_detailed", "installments", "payment_method_id", "payment_type_id", "updated_at"};
			resultados.add(titles);
			
			while(conn.rs.next()) {
				Object amount = conn.rs.getObject("amount");
				Object amount_iva = conn.rs.getObject("amount_iva");
				Object amount_total = conn.rs.getObject("amount_total");
				Object cod = conn.rs.getObject("cod");
				Object date = conn.rs.getString("date");
				Object purchase_id = conn.rs.getString("purchase_id");
				Object status = conn.rs.getString("status");
				Object user= conn.rs.getString("user");
				Object status_detailed = conn.rs.getObject("status_detailed");
				Object instalments= conn.rs.getObject("installments");
				Object payment_method_id= conn.rs.getObject("payment_method_id");
				Object payment_type_id= conn.rs.getObject("payment_type_id");
				Object updated_at= conn.rs.getObject("updated_at");
				
				Object[] filas = {amount, amount_iva, amount_total, cod, date, purchase_id, status, user, status_detailed, instalments,payment_method_id,payment_type_id, updated_at};
				resultados.add(filas);
			}
			
			int rowNum = 0;
	        for (Object[] rowData : resultados) {
	            Row row = sheet.createRow(rowNum++);
	            int colNum = 0;
	            for (Object field : rowData) {
	                Cell cell = row.createCell(colNum++);
	                if (field instanceof String) {
	                    cell.setCellValue((String) field);
	                } else if (field instanceof Integer) {
	                    cell.setCellValue((Integer) field);
	                } else if (field instanceof Double) {
	                    cell.setCellValue((Double) field);
	                }
	            }
	        }
	        String filePath = Utils.projectPath() + "/excel/exportPurchase"+date.toString().replaceAll("-", "").trim()+".xlsx";
	        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
	            
	        	workbout.write(fileOut);
	        } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//SELECT * FROM purchase ORDER BY date DESC
	
	

	
	
	
}
