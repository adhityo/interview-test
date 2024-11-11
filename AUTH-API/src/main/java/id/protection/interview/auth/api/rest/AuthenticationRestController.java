package id.protection.interview.auth.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import id.protection.interview.auth.api.service.AuthenticationService;

@RestController("authenticationRestController")
public class AuthenticationRestController {

	@Autowired
	private AuthenticationService authenticationService;
}
