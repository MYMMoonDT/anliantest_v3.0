package cn.edu.tongji.anliantest.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="jctzd_table")
public class JCTZDTable implements Serializable{

	private static final long serialVersionUID = -2674567823437784719L;
	
	private Long id;
	
	private Project project;
	
	private String tableNum;
	private String revisionStatus;
	
	private Date testStartDate;
	private Date testEndDate;
	
	private Employee notifyEmployee;
	private Employee reviewEmployee;
	private Employee receiveEmployee;
	
	private Date submitDate;
	private Date receiveDate;
	
	private List<JCTZDGroup> items = new ArrayList<JCTZDGroup>(0);
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@OneToOne
	@JoinColumn(name="projectId")
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public String getTableNum() {
		return tableNum;
	}
	public void setTableNum(String tableNum) {
		this.tableNum = tableNum;
	}
	public String getRevisionStatus() {
		return revisionStatus;
	}
	public void setRevisionStatus(String revisionStatus) {
		this.revisionStatus = revisionStatus;
	}
	@Temporal(TemporalType.DATE)
	public Date getTestStartDate() {
		return testStartDate;
	}
	public void setTestStartDate(Date testStartDate) {
		this.testStartDate = testStartDate;
	}
	@Temporal(TemporalType.DATE)
	public Date getTestEndDate() {
		return testEndDate;
	}
	public void setTestEndDate(Date testEndDate) {
		this.testEndDate = testEndDate;
	}
	
	@OneToOne
	@JoinColumn(name="notifyEmployeeId")
	public Employee getNotifyEmployee() {
		return notifyEmployee;
	}
	public void setNotifyEmployee(Employee notifyEmployee) {
		this.notifyEmployee = notifyEmployee;
	}
	
	@OneToOne
	@JoinColumn(name="reviewEmployeeId")
	public Employee getReviewEmployee() {
		return reviewEmployee;
	}
	public void setReviewEmployee(Employee reviewEmployee) {
		this.reviewEmployee = reviewEmployee;
	}
	
	@OneToOne
	@JoinColumn(name="receiveEmployeeId")
	public Employee getReceiveEmployee() {
		return receiveEmployee;
	}
	public void setReceiveEmployee(Employee receiveEmployee) {
		this.receiveEmployee = receiveEmployee;
	}
	@Temporal(TemporalType.DATE)
	public Date getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}
	@Temporal(TemporalType.DATE)
	public Date getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}
	
	@OrderBy
	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.DELETE})
	@JoinColumn(name="tableId")
	@Fetch(FetchMode.SUBSELECT)
	public List<JCTZDGroup> getItems() {
		return items;
	}
	public void setItems(List<JCTZDGroup> items) {
		this.items = items;
	}
}
