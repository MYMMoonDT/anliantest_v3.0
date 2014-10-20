package cn.edu.tongji.anliantest.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="task")
public class Task implements Serializable{

	private static final long serialVersionUID = 1812004276039776099L;
	
	private Long id;
	
	private boolean status; //Task状态: true:未完成  false:已完成
	private TaskTypeEnum type;
	
	private Project project;
	private ProjectStepEnum projectStep;
	private ProjectStatusEnum projectStatus;
	
	private Department department;
	private Employee employee;

	public Task() {}
	
	public Task(Project project, ProjectStepEnum projectStep, ProjectStatusEnum projectStatus, Department department) {
		this(project, projectStep, projectStatus, TaskTypeEnum.DEPARTMENT, department, null);
	}
	
	public Task(Project project, ProjectStepEnum projectStep, ProjectStatusEnum projectStatus, Employee employee) {
		this(project, projectStep, projectStatus, TaskTypeEnum.EMPLOYEE, null, employee);
	}
	
	public Task(Project project, ProjectStepEnum projectStep, ProjectStatusEnum projectStatus, TaskTypeEnum type, Department department, Employee employee) {
		this.status = true;
		this.project = project;
		this.projectStep = projectStep;
		this.projectStatus = projectStatus;
		
		this.type = type;
		if(this.type == TaskTypeEnum.DEPARTMENT) {
			this.department = department;
		}else if(this.type == TaskTypeEnum.EMPLOYEE){
			this.employee = employee;
		}
	}
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public TaskTypeEnum getType() {
		return type;
	}

	public void setType(TaskTypeEnum type) {
		this.type = type;
	}

	@ManyToOne
	@JoinColumn(name="projectId")
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	public ProjectStepEnum getProjectStep() {
		return projectStep;
	}

	public void setProjectStep(ProjectStepEnum projectStep) {
		this.projectStep = projectStep;
	}

	public ProjectStatusEnum getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(ProjectStatusEnum projectStatus) {
		this.projectStatus = projectStatus;
	}

	@ManyToOne
	@JoinColumn(name="departmentId")
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@ManyToOne
	@JoinColumn(name="employeeId")
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
