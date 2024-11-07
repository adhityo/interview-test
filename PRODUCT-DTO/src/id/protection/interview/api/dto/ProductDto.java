

package id.protection.interview.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(value=Include.NON_NULL)
@Setter
@Getter
public class ProductDto implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	public static final String STATUS_OPEN = "open";
	public static final String STATUS_ACKNOWLEDGE = "acknowledged";
	public static final String STATUS_APPROVED = "approved";
	public static final String STATUS_REJECTED = "rejected";

	
	public static final String DELETE_AUTH = "DELETEPRODUCT";
	public static final String CREATE_AUTH = "CREATEPRODUCT";
	public static final String DETAIL_AUTH = "DETAILPRODUCT";
	public static final String SEARCH_AUTH = "SEARCHPRODUCT";
	public static final String UPDATE_AUTH = "UPDATEPRODUCT";
	public static final String REJECT_AUTH = "REJECTPRODUCT";
	public static final String APPROVE_AUTH = "APPROVEPRODUCT";
	public static final String ACK_AUTH = "ACKNOWLEDGEPRODUCT";

	//Fields
		
	@JsonProperty("id")
	private String id;
			
	@JsonProperty("unique_code")
	private String uniqueCode;
			
	@JsonProperty("product_name")
	private String productName;
			
	@JsonProperty("product_sku")
	private String productSku;
			
	@JsonProperty("description")
	private String description;
			
	@JsonProperty("price")
	private Double price;
			
	@JsonProperty("stock")
	private Double inventoryTotal;
			
	@JsonProperty("booked_inventory_total")
	private Double bookedInventoryTotal;
			
	@JsonProperty("returned_inventory_total")
	private Double returnedInventoryTotal;
			
	@JsonProperty("status")
	private String status;
			
	@JsonProperty("createdAt")
	private Long createdTime;
			
	@JsonProperty("created_by")
	private String createdBy;
			
	@JsonProperty("modifiedAt")
	private Long modifiedTime;
			
	@JsonProperty("modified_by")
	private String modifiedBy;
			
	@JsonProperty("deletedAt")
	private Long deletedTime;
			
	@JsonProperty("deleted_by")
	private String deletedBy;
			
	@JsonProperty("deleted_status")
	private String deletedStatus;
	
	@JsonProperty("category")
	private CategoryDto category;
	
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
	
	@JsonProperty("version")
	private Integer version;
	
	
	public ProductDto (){}
	
	
	
}
