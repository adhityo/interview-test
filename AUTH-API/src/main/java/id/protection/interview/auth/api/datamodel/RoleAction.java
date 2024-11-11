package id.protection.interview.auth.api.datamodel;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import id.protection.interview.api.dto.RoleActionDto;
import id.protection.interview.api.util.Converter;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "role_action")
@Setter
@Getter
public class RoleAction implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	// Fields
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "role_code")
	private String roleCode;

	@Column(name = "action_code")
	private String actionCode;

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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	private Role roleId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "action_id")
	private Action actionId;

	public void loadDto(RoleActionDto dto) {
		if (dto != null) {

			id = Converter.parseToLong(dto.getId());

			roleCode = dto.getRoleCode();

			actionCode = dto.getActionCode();

			status = dto.getStatus();

			if (dto.getRoleId() != null && !dto.getRoleId().equalsIgnoreCase("")) {
				roleId = new Role();

				roleId.setId(Integer.valueOf(dto.getRoleId()));
			}

			if (dto.getActionId() != null && !dto.getActionId().equalsIgnoreCase("")) {
				actionId = new Action();

				actionId.setId(Integer.valueOf(dto.getActionId()));
			}

		}
	}

	public RoleActionDto convertDto() {
		RoleActionDto result = new RoleActionDto();

		if (id != null) {
			result.setId(id.toString());
		}

		result.setRoleCode(roleCode);

		result.setActionCode(actionCode);

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

		if (this.roleId != null) {
			result.setRoleId(this.roleId.getId().toString());
		}
		if (this.actionId != null) {
			result.setActionId(this.actionId.getId().toString());
		}

		// foreign affairs end

		return result;

	}
}