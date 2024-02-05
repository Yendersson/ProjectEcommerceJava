package ar.com.ecommerce.newEcommerce.utils;

import java.util.Date;

import ar.com.ecommerce.newEcommerce.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class jwt {
    private static String secretKey = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";
	
	public static String generateToken(User user) {
		long nowMillis = System.currentTimeMillis();
	    Date now = new Date(nowMillis);
        String token = Jwts.builder()
        		.claim("id", user.getId())
        		.claim("role", user.getRole())
        		.setSubject("USER")
        		.setIssuedAt(now)
        		.setExpiration(new Date(nowMillis + 600000))
        		.signWith(SignatureAlgorithm.HS256, secretKey).
        		compact();
        
        return token;
	}
	
	public static String getIdFromToken(String token) {
			String id = null;
			try {
				Claims claim = Jwts.parser()
						.setSigningKey(secretKey)
						.parseClaimsJws(token.replaceAll("\"", ""))
						.getBody();
				id = claim.get("id").toString();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return id;
			
	}
	
	public static Boolean evaluateToken(String token) {
			try {
				Claims claim = Jwts.parser()
						.setSigningKey(secretKey)
						.parseClaimsJws(token.replaceAll("\"", ""))
						.getBody();
				return true;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return false;
			}
			
	}
	public static void logoutToken(String token) {
		long nowMillis = System.currentTimeMillis();
		
		try {
			Jwts.parser()
			.setSigningKey(secretKey)
			.parseClaimsJws(token)
			.getBody().
			setExpiration(new Date(nowMillis));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
				

}
