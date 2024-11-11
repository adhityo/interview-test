package id.protection.interview.auth.api.datamodel;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import id.protection.interview.api.dto.RoleDto;
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
@Table(name = "role")
@Setter
@Getter
public class Role implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	// Fields
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "role_code")
	private String roleCode;

	@Column(name = "role_name")
	private String roleName;

	@Column(name = "description")
	private String description;

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

	public void loadDto(RoleDto dto) {
		if (dto != null) {

			id = Converter.parseToInteger(dto.getId());

			roleCode = dto.getRoleCode();

			roleName = dto.getRoleName();

			description = dto.getDescription();

		}
	}

	public RoleDto convertDto() {
		RoleDto result = new RoleDto();

		if (id != null) {
			result.setId(id.toString());
		}

		result.setRoleCode(roleCode);

		result.setRoleName(roleName);

		result.setDescription(description);

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