package cn.edu.tongji.anliantest.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;

/*
 *	《客户资料登记单》条目-实体类 
 */

@Entity
@Table(name="khzldjd_item")
public class KHZLDJDItem implements Serializable{

	private static final long serialVersionUID = -1508561612905418405L;
	
	private Long id;
	
	private KHZLDJDTable table;
	
	private String resourceName;
	private Date submitDate;
	private Integer resourceNum;
	private Date returnDate;
	
	private Employee receiveEmployee;
	private Employee returnEmployee;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(cascade = {CascadeType.REFRESH})
	@JoinColumn(name="tableId")
	@JsonIgnore
	public KHZLDJDTable getTable() {
		return table;
	}
	public void setTable(KHZLDJDTable table) {
		this.table = table;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
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
	
	@ManyToOne
	@JoinColumn(name="receiveEmployeeId")
	public Employee getReceiveEmployee() {
		return receiveEmployee;
	}
	public void setReceiveEmployee(Employee receiveEmployee) {
		this.receiveEmployee = receiveEmployee;
	}
	
	@ManyToOne
	@JoinColumn(name="returnEmployeeId")
	public Employee getReturnEmployee() {
		return returnEmployee;
	}
	public void setReturnEmployee(Employee returnEmployee) {
		this.returnEmployee = returnEmployee;
	}
	
	
}
