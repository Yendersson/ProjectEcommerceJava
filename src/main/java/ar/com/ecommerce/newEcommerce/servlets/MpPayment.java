package ar.com.ecommerce.newEcommerce.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MpPayment extends HttpServlet{
	
	public static final String ACCESS_TOKEN = "TEST-5251091904289939-071618-e48978c9f57e7e9776be1dff7c114bb5-1228085268";//PaymentMethod.getDataPropertie("ACCESS_TOKEN"); 
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		
		String body = req.getReader().readLine();
		String transaction = postDataUrl("https://api.mercadopago.com/v1/payments", body); 
		out.print(transaction);
		
		super.doPost(req, resp);
	}
	
	public static String getDataUrl(String url) throws IOException {
		return sendData(url, "GET", null);
	}
	
	public static String postDataUrl(String url, String data) throws IOException {
		return sendData(url, "POST", data);	
	}
	
	public static String sendData(String url, String method, String data) throws IOException {
			
			String returnData = null;
			URL urlConnect = new URL(url);
			HttpURLConnection con = (HttpURLConnection) urlConnect.openConnection();
			
			con.setRequestMethod(method);
			con.setRequestProperty("Content-type", "application/json");
			con.setRequestProperty("Authorization", "Bearer " +ACCESS_TOKEN);
			con.setRequestProperty("Accept", "application/json");
			
			if (method.equals("POST")) {
				con.setDoOutput(true);
				con.setRequestProperty("Content-length",String.valueOf(data.getBytes().length));
				OutputStream os = con.getOutputStream();
				os.write(data.getBytes());
				os.flush();
				os.close();
			}
			
			int responseCode = con.getResponseCode();
			System.out.println(method + " Response Code :: " + responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) { // success
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
	
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
	
				// print result
				returnData = response.toString();
			} else {
				
				BufferedReader errorReader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	            String errorLine;
	            StringBuilder errorResponse = new StringBuilder();
	
	            while ((errorLine = errorReader.readLine()) != null) {
	                errorResponse.append(errorLine);
	            }
	
	            errorReader.close();
				returnData = method + " " +errorResponse.toString();
			}
	
			return returnData;
			
		}
}
