
package id.protection.interview.api.datamodel;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import id.protection.interview.api.dto.ProductDto;
import id.protection.interview.api.util.Converter;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
	@Size(max = 255, message = "Name Length must not more than 255 characters")
	@NotEmpty(message = "Name is Empty")
	private String productName;

	@NotEmpty(message = "SKU is Empty")	
	@Column(name = "product_sku", unique = true)
	private String productSku;

	@Column(name = "description")
	private String description;

	@Column(name = "price")
	@NotEmpty(message = "Price is empty")
	@Positive(message = "Price Must Not Negative")
	private Long price;

	@Column(name = "inventory_total")
	@Positive(message = "Stock Must Not Negative")
	private Double inventoryTotal;

	@Column(name = "booked_inventory_total")
	private Double bookedInventoryTotal;

	@Column(name = "returned_inventory_total")
	private Double returnedInventoryTotal;

	@Column(name = "status")
	private String status;

	@Column(name = "created_time")
	private Long createdTime;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "modified_time")
	private Long modifiedTime;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "deleted_time")
	private Long deletedTime;

	@Column(name = "deleted_by")
	private String deletedBy;

	@Column(name = "deleted_status")
	private Integer deletedStatus;
	
	@Column(name="version")
	private Integer version;

	public void loadDto(ProductDto dto) {
		if (dto != null) {

			id = Long.valueOf(dto.getId());

			uniqueCode = dto.getUniqueCode();

			productName = dto.getProductName();

			productSku = dto.getProductSku();

			description = dto.getDescription();

			price = Converter.parseToLong(dto.getPrice());

			inventoryTotal = dto.getInventoryTotal();

			bookedInventoryTotal = dto.getBookedInventoryTotal();

			returnedInventoryTotal = dto.getReturnedInventoryTotal();

			status = dto.getStatus();

		}
	}

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

		result.setCreatedTime(createdTime);
		// converted
		result.setCreatedBy(createdBy);

		result.setModifiedTime(modifiedTime);
		// converted
		result.setModifiedBy(modifiedBy);

		result.setDeletedTime(deletedTime);
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