package cn.edu.tongji.anliantest.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customer_health_department")
public class CustomerHealthDepartment implements Serializable{
	private static final long serialVersionUID = 850300992128242777L;
	
	private Long id;
	
	private boolean exist;
	private String healthDepName;
	private int healthEmpNum;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public boolean isExist() {
		return exist;
	}
	public void setExist(boolean exist) {
		this.exist = exist;
	}
	
	public String getHealthDepName() {
		return healthDepName;
	}
	public void setHealthDepName(String healthDepName) {
		this.healthDepName = healthDepName;
	}
	public int getHealthEmpNum() {
		return healthEmpNum;
	}
	public void setHealthEmpNum(int healthEmpNum) {
		this.healthEmpNum = healthEmpNum;
	}
	
}
