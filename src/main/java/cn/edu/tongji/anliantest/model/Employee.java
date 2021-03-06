package cn.edu.tongji.anliantest.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {

	private static final long serialVersionUID = -7265330153237797718L;

	private Long id;
	private String name;
	private String number;
	private String password;
	private String title;

	private Department department;
	private EmployeeAuthorityGroup employeeAuthorityGroup;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@ManyToOne
	@JoinColumn(name = "departmentId")
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@OneToOne
	@JoinColumn(name = "employeeAuthorityGroupId")
	public EmployeeAuthorityGroup getEmployeeAuthorityGroup() {
		return employeeAuthorityGroup;
	}

	public void setEmployeeAuthorityGroup(EmployeeAuthorityGroup employeeAuthorityGroup) {
		this.employeeAuthorityGroup = employeeAuthorityGroup;
	}
}
