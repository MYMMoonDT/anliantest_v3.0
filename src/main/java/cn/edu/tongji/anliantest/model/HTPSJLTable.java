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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.edu.tongji.anliantest.util.TableNumEnum;

/*
 *	《合同评审记录》表-实体类 
 */

@Entity
@Table(name="htpsjl_table")
public class HTPSJLTable implements Serializable{

	private static final long serialVersionUID = 7952058132962244950L;

	private Long id;
	
	private Project project;
	
	private TableNumEnum tableNum;
	
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

	public TableNumEnum getTableNum() {
		return tableNum;
	}

	public void setTableNum(TableNumEnum tableNum) {
		this.tableNum = tableNum;
	}

	@Temporal(TemporalType.DATE)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "table")
	public Set<HTPSJLItem> getItems() {
		return items;
	}

	public void setItems(Set<HTPSJLItem> items) {
		this.items = items;
	}
}
