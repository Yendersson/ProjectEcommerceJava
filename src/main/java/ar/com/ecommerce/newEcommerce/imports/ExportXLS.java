package ar.com.ecommerce.newEcommerce.imports;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import ar.com.ecommerce.newEcommerce.JDBC.Connection;

public class ExportXLS {
	
	public static void main(String[] args) {
		ExportXLS xls = new ExportXLS();
		
		xls.importXLS();
		
	}
	Workbook workbout;
	Sheet sheet;
	
	public ExportXLS() {
		this.workbout = new HSSFWorkbook();
		sheet = workbout.createSheet("Hoja1");
	}
	
	public void importXLS() {
		 String filePath = "/home/yender/eclipse-workspace/ProjectEcommerceJava/TestExcel.xlsx";
		 
		 try (FileInputStream fis = new FileInputStream(filePath)){
			 
			workbout = new HSSFWorkbook(fis);
			sheet = workbout.getSheetAt(0);
			
            for (Row row : sheet) {
            	if (row.getRowNum() == 0) continue;
            	
                for (Cell cell : row) {
                    System.out.print(cell.toString() + "\t");
                }
                System.out.println();
            }
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	

	public void exportXLS() {
		List<Object[]> data = getManyData();
		
		int rowNum = 0;
        for (Object[] rowData : data) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (Object field : rowData) {
                Cell cell = row.createCell(colNum++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
                // Puedes agregar más tipos de datos según sea necesario
            }
        }

        String filePath = projectRoot() + "/TestExcel.xlsx";
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            
        	workbout.write(fileOut);
        } catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public static List<Object[]> getManyData() {
		
		Connection conn = new Connection();
		List<Object[]> resultados = new ArrayList<>();
		
		try {
			Object[] titles = {"Nombre", "Codigo", "Descripcion", "info", "Precio Actua", "IVA", "Categoria", "Subcategoria", "Resumen"};
			resultados.add(titles);

			while(conn.rs.next()) {
				System.out.println(conn.rs.getString("current_price"));
				Object title = conn.rs.getObject("title");
				Object cod = conn.rs.getObject("cod");
				Object description = conn.rs.getObject("description");
				Object info = conn.rs.getObject("info");
				Object currentPrice = conn.rs.getString("current_price");
				Object iva = conn.rs.getString("iva_price");
				Object category = conn.rs.getString("category");
				Object subcategory = conn.rs.getObject("subcategory");
				Object textSummary = conn.rs.getObject("text_summary");
				
				Object[] filas = {title, cod, description, info, currentPrice, iva, category, subcategory, textSummary};
				resultados.add(filas);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resultados;
	}
	
public static String projectRoot() {
		
		String root = null;
		
		try {
			Process process = Runtime.getRuntime().exec("pwd");
			BufferedReader lector = new BufferedReader(new InputStreamReader(process.getInputStream()));
			 String linea;
			 StringBuilder salida = new StringBuilder();
           while ((linea = lector.readLine()) != null) {
               salida.append(linea);
           }    
           root = salida.toString();
		} catch (Exception e) {
	
		}
		return root;
	}
}
