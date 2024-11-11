package id.protection.interview.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(value=Include.NON_NULL)
@Setter
@Getter
public class RoleDto implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	public static final String STATUS_OPEN = "open";
	public static final String STATUS_ACKNOWLEDGE = "acknowledged";
	public static final String STATUS_APPROVED = "approved";
	public static final String STATUS_REJECTED = "rejected";

	
	public static final String DELETE_AUTH = "DELETEROLE";
	public static final String CREATE_AUTH = "CREATEROLE";
	public static final String DETAIL_AUTH = "DETAILROLE";
	public static final String SEARCH_AUTH = "SEARCHROLE";
	public static final String UPDATE_AUTH = "UPDATEROLE";
	public static final String REJECT_AUTH = "REJECTROLE";
	public static final String APPROVE_AUTH = "APPROVEROLE";
	public static final String ACK_AUTH = "ACKNOWLEDGEROLE";

	//Fields
		
	@JsonProperty("id")
	private String id;
			
	@JsonProperty("role_code")
	private String roleCode;
			
	@JsonProperty("role_name")
	private String roleName;
			
	@JsonProperty("description")
	private String description;
			
	@JsonProperty("created_time")
	private String createdTime;
			
	@JsonProperty("created_by")
	private String createdBy;
			
	@JsonProperty("modified_time")
	private String modifiedTime;
			
	@JsonProperty("modified_by")
	private String modifiedBy;
			
	@JsonProperty("deleted_time")
	private String deletedTime;
			
	@JsonProperty("deleted_by")
	private String deletedBy;
			
	@JsonProperty("deleted_status")
	private String deletedStatus;
		
	
	
 	@JsonProperty("response_code")
	private String responseCode;
	
	@JsonProperty("response_description")
	private String responseDescription;
	
	@JsonProperty("response_transaction_id")
	private String responseTransactionId;
	
	@JsonProperty("reason")
	private String reason;

	@JsonProperty("decision")
	private String decision;
	
	
	public RoleDto (){}
	
	
	
}
