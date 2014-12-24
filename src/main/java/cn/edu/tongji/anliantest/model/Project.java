package cn.edu.tongji.anliantest.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="project")
public class Project implements Serializable{

	private static final long serialVersionUID = 541865413564044189L;

	private Long id;
	private String name;
	private String number;
	private ProjectTypeEnum type;
	
	private Date createDate;
	
	private Customer customer;
	
	private String companyAddress;
	private Set<CustomerContactPerson> contactPersonItems = new HashSet<CustomerContactPerson>(0);
	
	private String contractAmount;
	
	private ProjectStepEnum step;
	private ProjectStatusEnum status;
	
	private Employee businessEmployee;
	private Employee projectEmployee;
	
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

	public ProjectTypeEnum getType() {
		return type;
	}

	public void setType(ProjectTypeEnum type) {
		this.type = type;
	}

	@Temporal(TemporalType.DATE)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(String contractAmount) {
		this.contractAmount = contractAmount;
	}

	public ProjectStepEnum getStep() {
		return step;
	}

	public void setStep(ProjectStepEnum step) {
		this.step = step;
	}

	public ProjectStatusEnum getStatus() {
		return status;
	}

	public void setStatus(ProjectStatusEnum status) {
		this.status = status;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="businessEmployeeId")
	public Employee getBusinessEmployee() {
		return businessEmployee;
	}

	public void setBusinessEmployee(Employee businessEmployee) {
		this.businessEmployee = businessEmployee;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="projectEmployeeId")
	public Employee getProjectEmployee() {
		return projectEmployee;
	}

	public void setProjectEmployee(Employee projectEmployee) {
		this.projectEmployee = projectEmployee;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="customerId")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	
	@ManyToMany(fetch = FetchType.EAGER)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE})
	@JoinTable(name="project_contact_person",
		joinColumns={@JoinColumn(name="projectId")},
		inverseJoinColumns={@JoinColumn(name="contactPersonId")}
	)
	public Set<CustomerContactPerson> getContactPersonItems() {
		return contactPersonItems;
	}

	public void setContactPersonItems(Set<CustomerContactPerson> contactPersonItems) {
		this.contactPersonItems = contactPersonItems;
	}
}
