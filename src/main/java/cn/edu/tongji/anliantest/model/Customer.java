package cn.edu.tongji.anliantest.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="customer")
public class Customer implements Serializable{
	
	private static final long serialVersionUID = -7729887868125119209L;
	
	private Long id;

	private String companyName;
	private String companyAddress;
	private String companyType;
	private String companyIndustry;
	
	private int manageEmployeeNum;
	private int manufactureEmployeeNum;
	
	private CustomerHealthDepartment customerHealthDep;
	
	private Set<CustomerContactPerson> contactPersonItems = new HashSet<CustomerContactPerson>(0);
	private Set<CustomerProduct> productItems = new HashSet<CustomerProduct>(0);
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getCompanyIndustry() {
		return companyIndustry;
	}

	public void setCompanyIndustry(String companyIndustry) {
		this.companyIndustry = companyIndustry;
	}

	public int getManageEmployeeNum() {
		return manageEmployeeNum;
	}

	public void setManageEmployeeNum(int manageEmployeeNum) {
		this.manageEmployeeNum = manageEmployeeNum;
	}

	public int getManufactureEmployeeNum() {
		return manufactureEmployeeNum;
	}

	public void setManufactureEmployeeNum(int manufactureEmployeeNum) {
		this.manufactureEmployeeNum = manufactureEmployeeNum;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.DELETE})
	@JoinColumn(name = "customerHealthDepId")
	public CustomerHealthDepartment getCustomerHealthDep() {
		return customerHealthDep;
	}

	public void setCustomerHealthDep(CustomerHealthDepartment customerHealthDep) {
		this.customerHealthDep = customerHealthDep;
	}

	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.DELETE})
	@JoinColumn(name = "customerId")
	public Set<CustomerContactPerson> getContactPersonItems() {
		return contactPersonItems;
	}

	public void setContactPersonItems(Set<CustomerContactPerson> contactPersonItems) {
		this.contactPersonItems = contactPersonItems;
	}

	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.DELETE})
	@JoinColumn(name = "customerId")
	public Set<CustomerProduct> getProductItems() {
		return productItems;
	}

	public void setProductItems(Set<CustomerProduct> productItems) {
		this.productItems = productItems;
	}
}
