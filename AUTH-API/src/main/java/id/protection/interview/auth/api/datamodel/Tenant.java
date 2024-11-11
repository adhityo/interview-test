
package id.protection.interview.auth.api.datamodel;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import id.protection.interview.api.dto.TenantDto;
import id.protection.interview.api.util.Converter;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "tenant")
@Setter
@Getter
public class Tenant implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	// Fields
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "unique_code")
	private String uniqueCode;

	@Column(name = "tenant_name")
	private String tenantName;

	@Column(name = "tenant_target_route")
	private String tenantTargetRoute;

	@Column(name = "current_token")
	private String currentToken;

	@Column(name = "current_token_expire_time")
	private java.sql.Timestamp currentTokenExpireTime;

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

	public void loadDto(TenantDto dto) {
		if (dto != null) {

			id = Converter.parseToLong(dto.getId());

			uniqueCode = dto.getUniqueCode();

			tenantName = dto.getTenantName();

			tenantTargetRoute = dto.getTenantTargetRoute();

			currentToken = dto.getCurrentToken();

			if (dto.getCurrentTokenExpireTime() != null && !dto.getCurrentTokenExpireTime().equalsIgnoreCase("")) {
				currentTokenExpireTime = java.sql.Timestamp.valueOf(dto.getCurrentTokenExpireTime());
			}

			status = dto.getStatus();

		}
	}

	public TenantDto convertDto() {
		TenantDto result = new TenantDto();

		if (id != null) {
			result.setId(id.toString());
		}

		result.setUniqueCode(uniqueCode);

		result.setTenantName(tenantName);

		result.setTenantTargetRoute(tenantTargetRoute);

		result.setCurrentToken(currentToken);

		if (currentTokenExpireTime != null) {
			result.setCurrentTokenExpireTime(currentTokenExpireTime.toString());
		}
		// converted
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