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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/*
 *	《评价方案审核记录》表 - 实体类 
 */

@Entity
@Table(name="pjfashjl_table")
public class PJFASHJLTable implements Serializable{

	private static final long serialVersionUID = -5187728582489461836L;
	
	private Long id;
	
	private Project project;
	
	private String tableNum;
	private String revisionStatus;
	
	private String reviewHost;
	private String reviewAttendant;
	private String reviewWriter;
	
	private Date createDate;
	
	private String reviewRecord;
	private String reviewEmployee;
	private Date reviewDate;
	
	private String modifyResult;
	private String modifyEmployee;
	private Date modifyDate;
	
	private Set<PJFASHJLItem> items = new HashSet<PJFASHJLItem>();
	
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

	public String getReviewHost() {
		return reviewHost;
	}

	public void setReviewHost(String reviewHost) {
		this.reviewHost = reviewHost;
	}

	public String getReviewAttendant() {
		return reviewAttendant;
	}

	public void setReviewAttendant(String reviewAttendant) {
		this.reviewAttendant = reviewAttendant;
	}

	public String getReviewWriter() {
		return reviewWriter;
	}

	public void setReviewWriter(String reviewWriter) {
		this.reviewWriter = reviewWriter;
	}

	@Temporal(TemporalType.DATE)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getReviewRecord() {
		return reviewRecord;
	}

	public void setReviewRecord(String reviewRecord) {
		this.reviewRecord = reviewRecord;
	}

	@Temporal(TemporalType.DATE)
	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getModifyResult() {
		return modifyResult;
	}

	public void setModifyResult(String modifyResult) {
		this.modifyResult = modifyResult;
	}

	@Temporal(TemporalType.DATE)
	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.DELETE})
	@JoinColumn(name = "tableId")
	public Set<PJFASHJLItem> getItems() {
		return items;
	}

	public void setItems(Set<PJFASHJLItem> items) {
		this.items = items;
	}

	public String getReviewEmployee() {
		return reviewEmployee;
	}

	public void setReviewEmployee(String reviewEmployee) {
		this.reviewEmployee = reviewEmployee;
	}

	public String getModifyEmployee() {
		return modifyEmployee;
	}

	public void setModifyEmployee(String modifyEmployee) {
		this.modifyEmployee = modifyEmployee;
	}
}
