package id.protection.interview.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(value = Include.NON_NULL)
@Setter
@Getter
public class RoleActionDto implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	public static final String STATUS_OPEN = "open";
	public static final String STATUS_ACKNOWLEDGE = "acknowledged";
	public static final String STATUS_APPROVED = "approved";
	public static final String STATUS_REJECTED = "rejected";

	public static final String DELETE_AUTH = "DELETEROLEACTION";
	public static final String CREATE_AUTH = "CREATEROLEACTION";
	public static final String DETAIL_AUTH = "DETAILROLEACTION";
	public static final String SEARCH_AUTH = "SEARCHROLEACTION";
	public static final String UPDATE_AUTH = "UPDATEROLEACTION";
	public static final String REJECT_AUTH = "REJECTROLEACTION";
	public static final String APPROVE_AUTH = "APPROVEROLEACTION";
	public static final String ACK_AUTH = "ACKNOWLEDGEROLEACTION";

	// Fields

	@JsonProperty("id")
	private String id;

	@JsonProperty("role_code")
	private String roleCode;

	@JsonProperty("action_code")
	private String actionCode;

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

	private String roleId;

	private String actionId;

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

	public RoleActionDto() {
	}

}
