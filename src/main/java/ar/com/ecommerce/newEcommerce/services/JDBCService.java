package ar.com.ecommerce.newEcommerce.services;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public abstract class JDBCService {
	private final String url = "jdbc:mysql://localhost:3306/nc";
	private final  String usuario = "root";
	private final  String contraseña = "yender";
	private final  String driver = "com.mysql.cj.jdbc.Driver";
	
	protected Workbook workbout;
	protected Sheet sheet;
	
	protected ResultSet sql(String query) {
		java.sql.Connection conn = null;
    	try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, usuario, contraseña);
			return conn.prepareStatement(query).executeQuery();
    	} catch (ClassNotFoundException e) {
			throw new RuntimeException("No se encuentra el driver SQL", e);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} 
	}
}
