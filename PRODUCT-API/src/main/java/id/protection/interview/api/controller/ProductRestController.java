package id.protection.interview.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import id.protection.interview.api.datamodel.Product;
import id.protection.interview.api.dto.ProductDto;
import id.protection.interview.api.messaging.sender.AuthExceptionLogSender;
import id.protection.interview.api.messaging.sender.ExceptionLogSender;
import id.protection.interview.api.security.dto.ActionUser;
import id.protection.interview.api.service.ProductService;
import id.protection.interview.api.util.UserAuthorizationService;
import id.protection.interview.api.utility.ExceptionLoggerUtil;

@RestController("productRestController")
public class ProductRestController {
	private static final String CLASS_NAME = "ProductRestController";

	@Autowired
	private ProductService productService;

	@Autowired
	private ExceptionLogSender exceptionLogSender;

	@Autowired
	private AuthExceptionLogSender authExceptionLogSender;
	
	@Autowired
	private String authServiceLocation;

	@PutMapping(value = "/deleteProduct/")
	public ResponseEntity<String> deleteProduct(@RequestHeader(name = "Authorization") String token,
			@RequestHeader(name="X-Tenant") String tenant,
			@RequestBody String dataSubmission) throws NullPointerException {

		String username = "";
		try {

			ObjectMapper objectMapper = new ObjectMapper();

			ProductDto product = objectMapper.readValue(dataSubmission, ProductDto.class);

			String authUserResult = UserAuthorizationService.isAuthorized(token, ProductDto.DELETE_AUTH,
				authServiceLocation);
			ActionUser user = objectMapper.readValue(authUserResult, ActionUser.class);

			if (!user.isLoggedIn()) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}

			username = user.getUsername();

			if (!user.isAuthorized()) {
				ExceptionLoggerUtil.logAuthException(CLASS_NAME, username, ProductDto.DELETE_AUTH,
						"Unauthorized DELETE Product ", authExceptionLogSender);
				return new ResponseEntity<>("Unauthorized DELETEPRODUCT", HttpStatus.FORBIDDEN);
			}
			ProductDto res = productService.delete(product.getUniqueCode(), user);

			String result = objectMapper.writeValueAsString(res);

			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			ExceptionLoggerUtil.logException(e, CLASS_NAME, username, ProductDto.DELETE_AUTH, "Delete Product ",
					exceptionLogSender);
			String errorMessage = e.getMessage();
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@PutMapping(value = "/editProduct/")
	public ResponseEntity<String> editProduct(@RequestHeader(name = "Authorization") String token, 
			@RequestHeader(name="X-Tenant") String tenant,
			@RequestBody String dataSubmission) throws NullPointerException {

		String username = "";
		try {
			ObjectMapper objectMapper = new ObjectMapper();

			ProductDto product = objectMapper.readValue(dataSubmission, ProductDto.class);

			String authUserResult = UserAuthorizationService.isAuthorized(token, ProductDto.UPDATE_AUTH,
					authServiceLocation);
			ActionUser user = objectMapper.readValue(authUserResult, ActionUser.class);

			if (!user.isLoggedIn()) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}

			username = user.getUsername();

			if (!user.isAuthorized()) {
				ExceptionLoggerUtil.logAuthException(CLASS_NAME, username, ProductDto.UPDATE_AUTH,
						"Unauthorized UPDATE Product ", authExceptionLogSender);
				return new ResponseEntity<>("Unauthorized UPDATEPRODUCT", HttpStatus.FORBIDDEN);
			}

			Product object = productService.get(product.getUniqueCode());

			if (object == null) {
				return new ResponseEntity<>("Product Not Found", HttpStatus.OK);
			} else {
				String preActivityData = objectMapper.writeValueAsString(object.convertDto());

				object.loadDto(product);
				ProductDto result = productService.update(object, preActivityData, user);
				String response = objectMapper.writeValueAsString(result);

				return new ResponseEntity<>(response, HttpStatus.OK);
			}
		} catch (Exception e) {
			ExceptionLoggerUtil.logException(e, CLASS_NAME, username, ProductDto.UPDATE_AUTH, "Update Product ",
					exceptionLogSender);
			String errorMessage = e.getMessage();
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@PostMapping(value = "/createProduct/")
	public ResponseEntity<String> createProduct(@RequestHeader(name = "Authorization") String token,
			@RequestHeader(name="X-Tenant") String tenant,
			@RequestBody String dataSubmission) throws NullPointerException {

		ProductDto res = null;
		String username = "";

		try {
			ObjectMapper objectMapper = new ObjectMapper();

			ProductDto product = objectMapper.readValue(dataSubmission, ProductDto.class);

			String authUserResult = UserAuthorizationService.isAuthorized(token, ProductDto.CREATE_AUTH,
					authServiceLocation);
			ActionUser user = objectMapper.readValue(authUserResult, ActionUser.class);

			if (!user.isLoggedIn()) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}

			username = user.getUsername();

			if (!user.isAuthorized()) {
				ExceptionLoggerUtil.logAuthException(CLASS_NAME, username, ProductDto.CREATE_AUTH,
						"Unauthorized CREATE Product ", authExceptionLogSender);
				return new ResponseEntity<>("Unauthorized CREATEPRODUCT", HttpStatus.FORBIDDEN);
			}

			Product object = new Product();
			object.loadDto(product);

			res = productService.create(object, user);
			String response = objectMapper.writeValueAsString(res);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			ExceptionLoggerUtil.logException(e, CLASS_NAME, username, ProductDto.CREATE_AUTH, "Create Product ",
					exceptionLogSender);
			String errorMessage = e.getMessage();
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
