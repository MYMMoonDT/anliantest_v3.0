package cn.edu.tongji.anliantest.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="employee_authority_group_item")
public class EmployeeAuthorityGroupItem implements Serializable{
	
	private static final long serialVersionUID = 3943943775225283897L;
	
	private Long id;
	private Boolean isActive;
	
	private EmployeeAuthorityGroup employeeAuthorityGroup;
	private AuthorityItem authorityItem;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false)
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "employeeAuthorityGroupId")
	@JsonIgnore
	public EmployeeAuthorityGroup getEmployeeAuthorityGroup() {
		return employeeAuthorityGroup;
	}

	public void setEmployeeAuthorityGroup(
			EmployeeAuthorityGroup employeeAuthorityGroup) {
		this.employeeAuthorityGroup = employeeAuthorityGroup;
	}

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "authorityItemId")
	@JsonIgnore
	public AuthorityItem getAuthorityItem() {
		return authorityItem;
	}

	public void setAuthorityItem(AuthorityItem authorityItem) {
		this.authorityItem = authorityItem;
	}
}
