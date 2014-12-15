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
 *	《合同评审记录》表-实体类 
 */

@Entity
@Table(name="htpsjl_table")
public class HTPSJLTable implements Serializable{

	private static final long serialVersionUID = 7952058132962244950L;

	private Long id;
	
	private Project project;
	
	private String tableNum;
	private String revisionStatus;
	
	private Date createDate;
	
	private Set<HTPSJLItem> items = new HashSet<HTPSJLItem>(0);
	
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
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.DELETE})
	@JoinColumn(name = "tableId")
	public Set<HTPSJLItem> getItems() {
		return items;
	}

	public void setItems(Set<HTPSJLItem> items) {
		this.items = items;
	}
}
