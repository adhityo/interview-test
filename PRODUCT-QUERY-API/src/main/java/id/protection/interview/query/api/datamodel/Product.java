package id.protection.interview.query.api.datamodel;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import id.protection.interview.api.dto.CategoryDto;
import id.protection.interview.api.dto.ProductDto;
import id.protection.interview.api.util.Converter;
import lombok.Getter;
import lombok.Setter;

@Table
@Setter
@Getter
public class Product {
	
	
	@PrimaryKey
	private String uniqueCode;

	@Column
	private String productName;

	@Column
	private String productSku;

	@Column
	private String description;

	@Column
	private Long price;

	@Column
	private Double inventoryTotal;

	@Column
	private Double bookedInventoryTotal;

	@Column
	private Double returnedInventoryTotal;

	@Column
	private String status;

	@Column
	private Long createdTime;

	@Column
	private String createdBy;

	@Column
	private Long modifiedTime;

	@Column
	private String modifiedBy;

	@Column
	private Long deletedTime;

	@Column
	private String deletedBy;

	@Column
	private Integer deletedStatus;
	
	@Column
	private Integer version;

	@Column
	private String categoryId;
	
	@Column
	private String categoryName;

	public void load(ProductDto dto) {
		this.uniqueCode = dto.getUniqueCode();
		this.productName = dto.getProductName();
		this.productSku = dto.getProductSku();
		this.description = dto.getDescription();
		this.price = dto.getPrice() == null ? 0 : dto.getPrice().longValue();
		this.inventoryTotal = dto.getInventoryTotal();
		this.bookedInventoryTotal = dto.getBookedInventoryTotal();
		this.returnedInventoryTotal = dto.getReturnedInventoryTotal();
		this.status = dto.getStatus();
		this.deletedStatus = Integer.valueOf(dto.getDeletedStatus());
		this.version = dto.getVersion();
		
		this.categoryId = dto.getCategory() == null ? "": dto.getCategory().getId();
		this.categoryName = dto.getCategory() == null ? "": dto.getCategory().getName();
	}
	public ProductDto convertDto() {
		ProductDto result = new ProductDto();
		
		result.setId(this.uniqueCode);
		result.setProductName(this.productName);
		result.setPrice(Double.valueOf(price));
		result.setInventoryTotal(this.inventoryTotal);
		result.setBookedInventoryTotal(this.bookedInventoryTotal);
		result.setReturnedInventoryTotal(this.returnedInventoryTotal);
		result.setStatus(status);
		result.setProductSku(productSku);
		result.setDescription(description);
		result.setCreatedBy(createdBy);
		result.setDeletedStatus(Converter.convertToString(deletedStatus));
		result.setVersion(version);		
		
		CategoryDto category = new CategoryDto();
		category.setId(categoryId);
		category.setName(categoryName);
		
		result.setCategory(category);
		
		return result;
	}
}
