package id.protection.interview.auth.api.service;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import id.protection.interview.api.security.dto.ActionUser;
import id.protection.interview.auth.api.dao.TenantDao;
import id.protection.interview.auth.api.datamodel.Tenant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service("authenticationService")
public class AuthenticationService {
	private static final long EXPIRATIONTIME = 2*24*3600*1000; // 2 day expire days in millis

	@Value("${auth.secret.key}")
	private String secretKey;
	
	@Autowired
	private TenantDao tenantDao;

	public String generateToken(String tenant) {

		String token = "";
		SecretKey signingKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
		
		
		Tenant tenantObject = tenantDao.getTenant(tenant);
		
		if (tenantObject != null) {
		
			Timestamp expireTime = new Timestamp(System.currentTimeMillis() +EXPIRATIONTIME );
			
			token = Jwts.builder().setSubject(tenant)
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(expireTime.getTime())).signWith(signingKey).compact();
			
			tenantObject.setCurrentToken(token);
			tenantObject.setCurrentTokenExpireTime(expireTime);
			tenantDao.save(tenantObject);
		}
		
		return token;

	}

	public ActionUser authenticatUser(String token, String tenant) {
		ActionUser result = new ActionUser();
		result.setUsername(tenant);

		if (token != null) {
			Claims claims = extractTokenClaim(token);
			
			if (!isTokenExpired(claims)) {
			
				result.setLoggedIn(true);
			
				String tokenTenant = claims.getSubject();
				
				if (tokenTenant.equalsIgnoreCase(tenant)) {
					result.setAuthorized(true);
				}				
			}
		
		}
		

		return result;
	}
	public boolean isTokenExpired(Claims tokenClaims) {
		boolean result = false;
		
		if (tokenClaims.getExpiration().before(new Date())) {
			result = true;
		}
		
		return result;
	}
	public Claims extractTokenClaim (String token) {


		SecretKey signingKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
		
		return Jwts
                .parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
	}
}
