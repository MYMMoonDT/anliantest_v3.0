package cn.edu.tongji.anliantest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employee_authority_item")
public class EmployeeAuthorityItem implements Serializable {
	private static final long serialVersionUID = -7985100895602094246L;

	private Long id;
	private Boolean isActive = false;

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

	@ManyToOne
	@JoinColumn(name = "authorityItemId")
	public AuthorityItem getAuthorityItem() {
		return authorityItem;
	}

	public void setAuthorityItem(AuthorityItem authorityItem) {
		this.authorityItem = authorityItem;
	}

}
