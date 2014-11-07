package cn.edu.tongji.anliantest.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customer_product")
public class CustomerProduct implements Serializable{

	private static final long serialVersionUID = 24168994497922596L;
	
	private Long id;

	private String name;
	private String annualOutput;
	
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

	public String getAnnualOutput() {
		return annualOutput;
	}

	public void setAnnualOutput(String annualOutput) {
		this.annualOutput = annualOutput;
	}
}
