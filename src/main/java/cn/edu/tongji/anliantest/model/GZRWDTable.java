package cn.edu.tongji.anliantest.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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

import cn.edu.tongji.anliantest.util.TableNumEnum;

/*
 *	《工作任务单》表-实体类 
 */

@Entity
@Table(name="gzrwd_table")
public class GZRWDTable implements Serializable{

	private static final long serialVersionUID = -859378422614746932L;
	
	private Long id;
	
	private Project project;
	
	private TableNumEnum tableNum;
	
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

	public TableNumEnum getTableNum() {
		return tableNum;
	}

	public void setTableNum(TableNumEnum tableNum) {
		this.tableNum = tableNum;
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

	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "table")
	public Set<GZRWDItem> getItems() {
		return items;
	}

	public void setItems(Set<GZRWDItem> items) {
		this.items = items;
	}
}
