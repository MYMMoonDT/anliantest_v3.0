package cn.edu.tongji.anliantest.model;

import java.io.Serializable;
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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/*
 *	《客户资料登记单》表-实体类 
 */

@Entity
@Table(name="khzldjd_table")
public class KHZLDJDTable implements Serializable{
	
	private static final long serialVersionUID = -3190988802798037308L;

	private Long id;
	
	private Project project;
	
	private String tableNum;
	private String revisionStatus;
	
	private Set<KHZLDJDItem> items = new HashSet<KHZLDJDItem>(0);

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

	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.DELETE})
	@JoinColumn(name="tableId")
	public Set<KHZLDJDItem> getItems() {
		return items;
	}

	public void setItems(Set<KHZLDJDItem> items) {
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
