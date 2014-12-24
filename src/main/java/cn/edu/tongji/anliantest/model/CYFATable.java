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

@Entity
@Table(name="cyfa_table")
public class CYFATable implements Serializable{

	private static final long serialVersionUID = -2130953960831591909L;
	
	
	private Long id;

	private Project project;
	
	private String tableNum;
	private String revisionStatus;
	
	private Date sampleStartDate;
	private Date sampleEndDate;
	
	private Employee planEmployee;
	private Date planDate;
	
	private Employee reviewEmployee;
	private Date reviewDate;

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
	public Date getSampleStartDate() {
		return sampleStartDate;
	}


	public void setSampleStartDate(Date sampleStartDate) {
		this.sampleStartDate = sampleStartDate;
	}

	@Temporal(TemporalType.DATE)
	public Date getSampleEndDate() {
		return sampleEndDate;
	}


	public void setSampleEndDate(Date sampleEndDate) {
		this.sampleEndDate = sampleEndDate;
	}

	@OneToOne
	@JoinColumn(name="planEmployeeId")
	public Employee getPlanEmployee() {
		return planEmployee;
	}


	public void setPlanEmployee(Employee planEmployee) {
		this.planEmployee = planEmployee;
	}

	@Temporal(TemporalType.DATE)
	public Date getPlanDate() {
		return planDate;
	}


	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}

	@OneToOne
	@JoinColumn(name="reviewEmployeeId")
	public Employee getReviewEmployee() {
		return reviewEmployee;
	}


	public void setReviewEmployee(Employee reviewEmployee) {
		this.reviewEmployee = reviewEmployee;
	}

	@Temporal(TemporalType.DATE)
	public Date getReviewDate() {
		return reviewDate;
	}


	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}
	
}
