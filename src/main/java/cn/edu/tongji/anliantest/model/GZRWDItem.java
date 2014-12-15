package cn.edu.tongji.anliantest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 *	《工作任务单》条目-实体类 
 */

@Entity
@Table(name="gzrwd_item")
public class GZRWDItem implements Serializable{

	private static final long serialVersionUID = 6338070243699353142L;
	
	private Long id;
	
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
