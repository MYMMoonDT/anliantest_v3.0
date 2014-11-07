package cn.edu.tongji.anliantest.model.experiment;

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

import cn.edu.tongji.anliantest.model.Employee;
import cn.edu.tongji.anliantest.model.Project;

@Entity
@Table(name = "jcbg_table")
public class JCBGTable implements java.io.Serializable{

	private static final long serialVersionUID = 2067928682765081368L;

	private Long id;
	
	private Project project;
	
	private Date reportTime;
	
	private Employee prepareEmployee;   //编制人
	private Employee reviewEmployee;    //审核人
	private Employee signEmployee;      //签发人
	
	private Set<JCBGItem> items = new HashSet<JCBGItem>(0);

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

	@Temporal(TemporalType.DATE)
	public Date getReportTime() {
		return reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	@OneToOne
	@JoinColumn(name="prepareEmployeeId")
	public Employee getPrepareEmployee() {
		return prepareEmployee;
	}

	public void setPrepareEmployee(Employee prepareEmployee) {
		this.prepareEmployee = prepareEmployee;
	}

	public Employee getReviewEmployee() {
		return reviewEmployee;
	}

	@OneToOne
	@JoinColumn(name="reviewEmployeeId")
	public void setReviewEmployee(Employee reviewEmployee) {
		this.reviewEmployee = reviewEmployee;
	}

	public Employee getSignEmployee() {
		return signEmployee;
	}

	@OneToOne
	@JoinColumn(name="signEmployeeId")
	public void setSignEmployee(Employee signEmployee) {
		this.signEmployee = signEmployee;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "table")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.DELETE})
	public Set<JCBGItem> getItems() {
		return items;
	}

	public void setItems(Set<JCBGItem> items) {
		this.items = items;
	}
}
