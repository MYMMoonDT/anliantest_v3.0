package cn.edu.tongji.anliantest.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="department")
public class Department implements Serializable{

	private static final long serialVersionUID = -3508465035547176939L;
	
	private Long id;
	private String name;
	private String description;
	
	private DepartmentTypeEnum type;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public DepartmentTypeEnum getType() {
		return type;
	}
	public void setType(DepartmentTypeEnum type) {
		this.type = type;
	}

}
