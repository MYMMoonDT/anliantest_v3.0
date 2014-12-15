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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/*
 *	《工作任务单》表-实体类 
 */

@Entity
@Table(name="gzrwd_table")
public class GZRWDTable implements Serializable{

	private static final long serialVersionUID = -859378422614746932L;
	
	private Long id;
	
	private Project project;
	
	private String tableNum;
	private String revisionStatus;
	
	private Employee issueEmployee;
	
	private Date issueDate;
	
	private Set<GZRWDItem> items = new HashSet<GZRWDItem>(0);

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

	@ManyToOne
	@JoinColumn(name="issueEmployeeId")
	public Employee getIssueEmployee() {
		return issueEmployee;
	}

	public void setIssueEmployee(Employee issueEmployee) {
		this.issueEmployee = issueEmployee;
	}

	@Temporal(TemporalType.DATE)
	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.DELETE})
	@JoinColumn(name="tableId")
	public Set<GZRWDItem> getItems() {
		return items;
	}

	public void setItems(Set<GZRWDItem> items) {
		this.items = items;
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
}
