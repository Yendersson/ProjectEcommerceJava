package ar.com.ecommerce.newEcommerce.services;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import ar.com.ecommerce.newEcommerce.utils.Utils;

@Service
public class ExportProductsService extends JDBCService {

	public List<Object[]> exportProducts() {
		List<Object[]> resultados = new ArrayList<>();
		ResultSet rs = sql("SELECT * FROM product ORDER BY title DESC");
		Object[] titles = { "amount", "amount_iva", "amount_total", "cod", "date", "purchase_id", "status", "user",
				"status_detailed", "installments", "payment_method_id", "payment_type_id", "updated_at" };
		
		resultados.add(titles);

		try {
			while (rs.next()) {
				Object amount = rs.getObject("amount");
				Object amount_iva = rs.getObject("amount_iva");
				Object amount_total = rs.getObject("amount_total");
				Object cod = rs.getObject("cod");
				Object date = rs.getString("date");
				Object purchase_id = rs.getString("purchase_id");
				Object status = rs.getString("status");
				Object user = rs.getString("user");
				Object status_detailed = rs.getObject("status_detailed");
				Object instalments = rs.getObject("installments");
				Object payment_method_id = rs.getObject("payment_method_id");
				Object payment_type_id = rs.getObject("payment_type_id");
				Object updated_at = rs.getObject("updated_at");

				Object[] filas = { amount, amount_iva, amount_total, cod, date, purchase_id, status, user,
						status_detailed, instalments, payment_method_id, payment_type_id, updated_at };
				
				resultados.add(filas);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Problemas con el sql");
		}
		return resultados;
	}

	public byte[] createXLSFile() {
		
		List<Object[]> records = exportProducts();
		try {
			this.workbout = new HSSFWorkbook();
			this.sheet = workbout.createSheet("Hoja 1");
	
			int rowNum = 0;
			for (Object[] rowrecords : records) {
				Row row = sheet.createRow(rowNum++);
				int colNum = 0;
				for (Object field : rowrecords) {
					Cell cell = row.createCell(colNum++);
					if (field instanceof String) {
						cell.setCellValue((String) field);
					} else if (field instanceof Integer) {
						cell.setCellValue((Integer) field);
					} else if (field instanceof Double) {
						cell.setCellValue((Double) field);
					} else if (field instanceof Long) {
						cell.setCellValue((Long) field);
					}
				}
			}
		
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			workbout.write(outputStream);
			return outputStream.toByteArray();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}

		/*String filePath = Utils.projectPath() + "/products"+new java.util.Date().getTime()+ ".xlsx";
		try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
			workbout.write(fileOut);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e.getMessage());
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}*/
	}

}
