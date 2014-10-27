package cn.edu.tongji.anliantest.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.edu.tongji.anliantest.util.TableNumEnum;

/*
 *	《现场调查表》表-实体类 
 */

@Entity
@Table(name="xcdcjl_table")
public class XCDCJLTable implements Serializable{

	private static final long serialVersionUID = 7528595721843617698L;
	
	private Long id;
	
	private Project project;
	
	private TableNumEnum tableNum;

	private Date createDate;
	
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
}
