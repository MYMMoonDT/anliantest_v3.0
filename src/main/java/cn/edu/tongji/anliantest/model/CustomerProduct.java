package cn.edu.tongji.anliantest.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="customer_product")
public class CustomerProduct implements Serializable{

	private static final long serialVersionUID = 24168994497922596L;
	
	private Long id;

	private String name;
	private String annualOutput;
	
	private Customer customer;
	
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

	@ManyToOne
	@JoinColumn(name="customerId")
	@JsonIgnore
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
