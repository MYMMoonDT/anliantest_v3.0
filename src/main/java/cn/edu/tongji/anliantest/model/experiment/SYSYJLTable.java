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
@Table(name="sysyjl_table")
public class SYSYJLTable implements Serializable{
	private static final long serialVersionUID = -2641958340627187287L;
	
	private Long id;
	
	private Project project;
	
	private String tableNum;
	private String revisionStatus;
	
	private String sampleCompanyName;
    private String sampleCompanyAddress;
    private String sampleName;
    
    private Employee sendSampleEmployee;
    private Employee receiveSampleEmployee;

    private Date sampleStartDate;
    private Date sampleEndDate;
    
    private Date sendSampleDate;
    private Date receiveSampleDate;
    
    private List<SYSYJLDay> items = new ArrayList<SYSYJLDay>(0);
    
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
	public String getSampleCompanyName() {
		return sampleCompanyName;
	}
	public void setSampleCompanyName(String sampleCompanyName) {
		this.sampleCompanyName = sampleCompanyName;
	}
	public String getSampleCompanyAddress() {
		return sampleCompanyAddress;
	}
	public void setSampleCompanyAddress(String sampleCompanyAddress) {
		this.sampleCompanyAddress = sampleCompanyAddress;
	}
	public String getSampleName() {
		return sampleName;
	}
	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}
	
	@OneToOne
	@JoinColumn(name="sendSampleEmployeeId")
	public Employee getSendSampleEmployee() {
		return sendSampleEmployee;
	}
	public void setSendSampleEmployee(Employee sendSampleEmployee) {
		this.sendSampleEmployee = sendSampleEmployee;
	}
	
	@OneToOne
	@JoinColumn(name="receiveSampleEmployeeId")
	public Employee getReceiveSampleEmployee() {
		return receiveSampleEmployee;
	}
	public void setReceiveSampleEmployee(Employee receiveSampleEmployee) {
		this.receiveSampleEmployee = receiveSampleEmployee;
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
	
	@Temporal(TemporalType.DATE)
	public Date getSendSampleDate() {
		return sendSampleDate;
	}
	public void setSendSampleDate(Date sendSampleDate) {
		this.sendSampleDate = sendSampleDate;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getReceiveSampleDate() {
		return receiveSampleDate;
	}
	public void setReceiveSampleDate(Date receiveSampleDate) {
		this.receiveSampleDate = receiveSampleDate;
	}
	
	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.DELETE})
	@JoinColumn(name="tableId")
	@Fetch(FetchMode.SUBSELECT)
	public List<SYSYJLDay> getItems() {
		return items;
	}
	public void setItems(List<SYSYJLDay> items) {
		this.items = items;
	}
	public Boolean getConfirm() {
		return confirm;
	}
	public void setConfirm(Boolean confirm) {
		this.confirm = confirm;
	}
}
