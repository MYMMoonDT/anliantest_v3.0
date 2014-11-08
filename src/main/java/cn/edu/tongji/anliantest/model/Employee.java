package cn.edu.tongji.anliantest.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "employee")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Employee implements Serializable {

	private static final long serialVersionUID = -7265330153237797718L;

	private Long id;
	private String name;
	private String number;
	private String password;
	private String title;

	private Department department;
	private Set<EmployeeAuthorityGroup> employeeAuthorityGroups = new HashSet<EmployeeAuthorityGroup>(0);

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

	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(value = {CascadeType.REFRESH})
	@JoinColumn(name = "departmentId")
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "employee", orphanRemoval = true)	
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.DELETE})
	@OrderBy("id")
	public Set<EmployeeAuthorityGroup> getEmployeeAuthorityGroups() {
		return employeeAuthorityGroups;
	}

	public void setEmployeeAuthorityGroups(
			Set<EmployeeAuthorityGroup> employeeAuthorityGroups) {
		this.employeeAuthorityGroups = employeeAuthorityGroups;
	}

}
