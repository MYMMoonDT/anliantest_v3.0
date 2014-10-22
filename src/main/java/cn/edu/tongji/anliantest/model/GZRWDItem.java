package cn.edu.tongji.anliantest.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

/*
 *	《工作任务单》条目-实体类 
 */

@Entity
@Table(name="gzrwd_item")
public class GZRWDItem implements Serializable{

	private static final long serialVersionUID = 6338070243699353142L;
	
	private Long id;
	
	private GZRWDTable table;
	
	private String groupName;
	private String workContent;
	private String workTimeLimit;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(cascade = {CascadeType.REFRESH})
	@JoinColumn(name="tableId")
	@JsonIgnore
	public GZRWDTable getTable() {
		return table;
	}

	public void setTable(GZRWDTable table) {
		this.table = table;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(columnDefinition = "TEXT")
	public String getWorkContent() {
		return workContent;
	}

	public void setWorkContent(String workContent) {
		this.workContent = workContent;
	}

	public String getWorkTimeLimit() {
		return workTimeLimit;
	}

	public void setWorkTimeLimit(String workTimeLimit) {
		this.workTimeLimit = workTimeLimit;
	}
}
