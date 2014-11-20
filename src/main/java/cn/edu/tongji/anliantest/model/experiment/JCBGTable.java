package cn.edu.tongji.anliantest.model.experiment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
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

import cn.edu.tongji.anliantest.model.Employee;
import cn.edu.tongji.anliantest.model.Project;

@Entity
@Table(name = "jcbg_table")
public class JCBGTable implements java.io.Serializable{

	private static final long serialVersionUID = 2067928682765081368L;

	private Long id;
	
	private Project project;
	
	private String tableNum;
	
	private Date sampleStartDate;
	private Date sampleEndDate;
	
	private Date testStartDate;
	private Date testEndDate;
	
	private Date reportDate;
	
	private Employee prepareEmployee;   //编制人
	private Employee reviewEmployee;    //审核人
	private Employee signEmployee;      //签发人
	
	private List<JCBGItem> items = new ArrayList<JCBGItem>(0);

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

	@Temporal(TemporalType.DATE)
	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	@OneToOne
	@JoinColumn(name="prepareEmployeeId")
	public Employee getPrepareEmployee() {
		return prepareEmployee;
	}

	public void setPrepareEmployee(Employee prepareEmployee) {
		this.prepareEmployee = prepareEmployee;
	}

	public Employee getReviewEmployee() {
		return reviewEmployee;
	}

	@OneToOne
	@JoinColumn(name="reviewEmployeeId")
	public void setReviewEmployee(Employee reviewEmployee) {
		this.reviewEmployee = reviewEmployee;
	}

	public Employee getSignEmployee() {
		return signEmployee;
	}

	@OneToOne
	@JoinColumn(name="signEmployeeId")
	public void setSignEmployee(Employee signEmployee) {
		this.signEmployee = signEmployee;
	}

	@OrderBy
	@OneToMany
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.DELETE})
	@JoinColumn(name="tableId")
	public List<JCBGItem> getItems() {
		return items;
	}

	public void setItems(List<JCBGItem> items) {
		this.items = items;
	}

	public String getTableNum() {
		return tableNum;
	}

	public void setTableNum(String tableNum) {
		this.tableNum = tableNum;
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

}
