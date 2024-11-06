
package id.protection.interview.api.datamodel;

import id.protection.interview.api.dto.ProductDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product")
@Setter
@Getter
public class Product implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	// Fields
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "unique_code")
	private String uniqueCode;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "product_sku")
	private String productSku;

	@Column(name = "description")
	private String description;

	@Column(name = "price")
	private Long price;

	@Column(name = "inventory_total")
	private Double inventoryTotal;

	@Column(name = "booked_inventory_total")
	private Double bookedInventoryTotal;

	@Column(name = "returned_inventory_total")
	private Double returnedInventoryTotal;

	@Column(name = "status")
	private String status;

	@Column(name = "created_time")
	private java.sql.Timestamp createdTime;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "modified_time")
	private java.sql.Timestamp modifiedTime;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "deleted_time")
	private java.sql.Timestamp deletedTime;

	@Column(name = "deleted_by")
	private String deletedBy;

	@Column(name = "deleted_status")
	private Integer deletedStatus;


	public ProductDto convertDto() {
		ProductDto result = new ProductDto();

		if (id != null) {
			result.setId(id.toString());
		}

		result.setUniqueCode(uniqueCode);

		result.setProductName(productName);

		result.setProductSku(productSku);

		result.setDescription(description);

		if (price != null) {
			result.setPrice(Double.valueOf(price.doubleValue()));
		}
		// converted
		result.setInventoryTotal(inventoryTotal);

		result.setBookedInventoryTotal(bookedInventoryTotal);

		result.setReturnedInventoryTotal(returnedInventoryTotal);

		result.setStatus(status);

		if (createdTime != null) {
			result.setCreatedTime(createdTime.toString());
		}
		// converted
		result.setCreatedBy(createdBy);

		if (modifiedTime != null) {
			result.setModifiedTime(modifiedTime.toString());
		}
		// converted
		result.setModifiedBy(modifiedBy);

		if (deletedTime != null) {
			result.setDeletedTime(deletedTime.toString());
		}
		// converted
		result.setDeletedBy(deletedBy);

		if (deletedStatus != null) {
			result.setDeletedStatus(deletedStatus.toString());
		}
		// converted

		// foreign affairs

		// foreign affairs end

		return result;

	}
}