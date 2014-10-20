package cn.edu.tongji.anliantest.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;

/*
 *	《合同评审记录》条目-实体类 
 */

@Entity
@Table(name="htpsjl_item")
public class HTPSJLItem implements Serializable{

	private static final long serialVersionUID = -2270902526185059724L;
	
	private Long id;
	
	private HTPSJLTable table;
	
	private String reviewContent;
	private String reviewComment;
	
	private Department department;
	private Employee employee;
	
	private Date createDate;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name="tableId")
	@JsonIgnore
	public HTPSJLTable getTable() {
		return table;
	}

	
	public void setTable(HTPSJLTable table) {
		this.table = table;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public String getReviewComment() {
		return reviewComment;
	}

	public void setReviewComment(String reviewComment) {
		this.reviewComment = reviewComment;
	}
	
	@ManyToOne
	@JoinColumn(name="departmentId")
	public Department getDepartment() {
		return department;
	}

	@ManyToOne
	@JoinColumn(name="employeeId")
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Temporal(TemporalType.DATE)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
