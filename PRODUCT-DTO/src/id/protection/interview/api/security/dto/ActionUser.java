package id.protection.interview.api.security.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(value=Include.NON_NULL)
public class ActionUser {

	@JsonProperty("responseCode")
	private String responseCode;
	
	@JsonProperty("username")
	private String username;
	
	@JsonProperty("requestTransactionId")
	private String requestTransactionId;
	
	@JsonProperty("responseDescription")
	private String responseDescription;
	
	@JsonProperty("isAuthorized")
	private boolean isAuthorized;
	
	@JsonProperty("isLoggedIn")
	private boolean isLoggedIn;
	
	public ActionUser() {
		this.isAuthorized = false;
		this.isLoggedIn = false;
	}
}
