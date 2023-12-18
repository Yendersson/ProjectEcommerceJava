package ar.com.ecommerce.newEcommerce.JDBC;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connection {
	private final String url = "jdbc:mysql://localhost:3306/nc";
	private final  String usuario = "root";
	private final  String contraseña = "yender";
	private final  String driver = "com.mysql.cj.jdbc.Driver";
    public java.sql.Connection connect = null;
    public PreparedStatement ps = null;
    public ResultSet rs = null;
    
    public Connection() {
    	
    	try {
			Class.forName(driver);
			
			connect = DriverManager.getConnection(url, usuario, contraseña);
			
			ps = connect.prepareStatement("SELECT * FROM product");
			System.out.println(ps);
			rs = ps.executeQuery();
			System.out.println(rs);
			
		} catch (Exception e) {
			try {
                if (ps != null) {
                    ps.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (SQLException es) {
                es.printStackTrace();
            }
		}
    	
	}
    
    
}
