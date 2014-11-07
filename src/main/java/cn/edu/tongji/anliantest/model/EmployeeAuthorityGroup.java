package cn.edu.tongji.anliantest.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
@Entity
@Table(name = "employee_authority_group")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class EmployeeAuthorityGroup implements Serializable {

	private static final long serialVersionUID = -2366248862481602675L;

	private Long id;
	private String name;
	private Boolean isActive;

	private Employee employee;
	
	private Set<EmployeeAuthorityGroupItem> employeeAuthorityGroupItems = new HashSet<EmployeeAuthorityGroupItem>(0);

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = false)
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@ManyToOne
	@Cascade(value = {CascadeType.REFRESH})
	@JoinColumn(name = "employeeId")
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
			this.employee = employee;
	}

	
	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.DELETE})
	@JoinColumn(name = "employeeAuthorityGroupId")
	public Set<EmployeeAuthorityGroupItem> getEmployeeAuthorityGroupItems() {
		return employeeAuthorityGroupItems;
	}

	public void setEmployeeAuthorityGroupItems(
			Set<EmployeeAuthorityGroupItem> employeeAuthorityGroupItems) {
		this.employeeAuthorityGroupItems = employeeAuthorityGroupItems;
	}
}
