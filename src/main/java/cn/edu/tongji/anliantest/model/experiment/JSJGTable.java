package cn.edu.tongji.anliantest.model.experiment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import cn.edu.tongji.anliantest.model.Employee;
import cn.edu.tongji.anliantest.model.Project;

@Entity
@Table(name = "jsjg_table")
public class JSJGTable implements java.io.Serializable{

	private static final long serialVersionUID = 2641893023123809327L;
	
	private Long id;
	
	private Project project;
	
	private String tableNum;
	
	private Employee calculateEmployee;
	private Employee reviewEmployee;
	
	private Date calculateTime;
	private Date reviewTime;

	private List<JSJGGroup> groups = new ArrayList<JSJGGroup>(0);
	
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

	@OneToOne
	@JoinColumn(name="calculateEmployeeId")
	public Employee getCalculateEmployee() {
		return calculateEmployee;
	}

	public void setCalculateEmployee(Employee calculateEmployee) {
		this.calculateEmployee = calculateEmployee;
	}

	@OneToOne
	@JoinColumn(name="reviewEmployeeId")
	public Employee getReviewEmployee() {
		return reviewEmployee;
	}

	public void setReviewEmployee(Employee reviewEmployee) {
		this.reviewEmployee = reviewEmployee;
	}

	@Temporal(TemporalType.DATE)
	public Date getCalculateTime() {
		return calculateTime;
	}

	public void setCalculateTime(Date calculateTime) {
		this.calculateTime = calculateTime;
	}

	@Temporal(TemporalType.DATE)
	public Date getReviewTime() {
		return reviewTime;
	}

	public void setReviewTime(Date reviewTime) {
		this.reviewTime = reviewTime;
	}

	@OrderBy
	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.DELETE})
	@JoinColumn(name="tableId")
	@Fetch(FetchMode.SUBSELECT)
	public List<JSJGGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<JSJGGroup> groups) {
		this.groups = groups;
	}
	
}
