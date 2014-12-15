package cn.edu.tongji.anliantest.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/*
 *	《客户资料登记单》条目-实体类 
 */

@Entity
@Table(name="khzldjd_item")
public class KHZLDJDItem implements Serializable{

	private static final long serialVersionUID = -1508561612905418405L;
	
	private Long id;
	
	private FileGroup resource;
	private Date submitDate;
	private Integer resourceNum;
	private Date returnDate;
	
	private String receiveEmployee;
	private String returnEmployee;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}
	public Integer getResourceNum() {
		return resourceNum;
	}
	public void setResourceNum(Integer resourceNum) {
		this.resourceNum = resourceNum;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	
	@OneToOne
	@JoinColumn(name="fileGroupId")
	public FileGroup getResource() {
		return resource;
	}
	public void setResource(FileGroup resource) {
		this.resource = resource;
	}
	public String getReturnEmployee() {
		return returnEmployee;
	}
	public void setReturnEmployee(String returnEmployee) {
		this.returnEmployee = returnEmployee;
	}
	public String getReceiveEmployee() {
		return receiveEmployee;
	}
	public void setReceiveEmployee(String receiveEmployee) {
		this.receiveEmployee = receiveEmployee;
	}
	
	
}
