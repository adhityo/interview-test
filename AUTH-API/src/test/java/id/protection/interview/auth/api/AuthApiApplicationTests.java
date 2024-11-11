package id.protection.interview.auth.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import id.protection.interview.api.security.dto.ActionUser;
import id.protection.interview.auth.api.dao.TenantDao;
import id.protection.interview.auth.api.datamodel.Tenant;
import id.protection.interview.auth.api.service.AuthenticationService;
import io.jsonwebtoken.Claims;

@SpringBootTest
class AuthApiApplicationTests {

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private TenantDao tenantDao;

	@Test
	void testAuthService() {

		String tenant = "tenant-a";

		try {


			Tenant tenantObject = tenantDao.getTenant(tenant);

			String token = authenticationService.generateToken(tenant);
			
			assertEquals(token, tenantObject.getCurrentToken());

			Claims claims = authenticationService.extractTokenClaim(token);

			assertEquals(claims.getSubject(), tenant);
			
			// do testing
			
			ActionUser actionUser = authenticationService.authenticatUser(token,tenant);
			
			assertEquals(actionUser.getUsername(), tenant);
			assertEquals(actionUser.isLoggedIn(), true);
			assertEquals(actionUser.isAuthorized(), true);

		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

}
