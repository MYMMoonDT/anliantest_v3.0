package cn.edu.tongji.anliantest.model.experiment;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import cn.edu.tongji.anliantest.model.Employee;
import cn.edu.tongji.anliantest.model.Project;

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
	
	private List<CYFAGroup> items = new ArrayList<CYFAGroup>(0);
	
	private Employee planEmployee;
	private Date planDate;
	
	private Employee reviewEmployee;
	private Date reviewDate;
	
	private Boolean confirm;

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

	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.DELETE})
	@JoinColumn(name="tableId")
	@Fetch(FetchMode.SUBSELECT)
	public List<CYFAGroup> getItems() {
		return items;
	}


	public void setItems(List<CYFAGroup> items) {
		this.items = items;
	}


	public Boolean getConfirm() {
		return confirm;
	}


	public void setConfirm(Boolean confirm) {
		this.confirm = confirm;
	}
	
}
