package id.protection.interview.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(value=Include.NON_NULL)
@Setter
@Getter
public class ActionDto implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	public static final String STATUS_OPEN = "open";
	public static final String STATUS_ACKNOWLEDGE = "acknowledged";
	public static final String STATUS_APPROVED = "approved";
	public static final String STATUS_REJECTED = "rejected";

	
	public static final String DELETE_AUTH = "DELETEACTION";
	public static final String CREATE_AUTH = "CREATEACTION";
	public static final String DETAIL_AUTH = "DETAILACTION";
	public static final String SEARCH_AUTH = "SEARCHACTION";
	public static final String UPDATE_AUTH = "UPDATEACTION";
	public static final String REJECT_AUTH = "REJECTACTION";
	public static final String APPROVE_AUTH = "APPROVEACTION";
	public static final String ACK_AUTH = "ACKNOWLEDGEACTION";

	//Fields
		
	@JsonProperty("id")
	private String id;
			
	@JsonProperty("action_name")
	private String actionName;
			
	@JsonProperty("action_code")
	private String actionCode;
			
	@JsonProperty("description")
	private String description;
			
	@JsonProperty("status")
	private String status;
			
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
	
	
	public ActionDto (){}
	
	
	
}
