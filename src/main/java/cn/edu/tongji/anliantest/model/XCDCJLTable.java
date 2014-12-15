package cn.edu.tongji.anliantest.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/*
 *	《现场调查表》表-实体类 
 */

@Entity
@Table(name="xcdcjl_table")
public class XCDCJLTable implements Serializable{

	private static final long serialVersionUID = 7528595721843617698L;
	
	private Long id;
	
	private Project project;
	
	private String tableNum;
	private String revisionStatus;

	private String supportResourceContent;
	private String processFlowContent;
	private String sourceListContent;
	private FileGroup layoutFile;
	
	private String investigateEmployee;
	private String attendEmployee;
	
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

	@Temporal(TemporalType.DATE)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	@Column(columnDefinition = "TEXT")
	public String getSupportResourceContent() {
		return supportResourceContent;
	}

	public void setSupportResourceContent(String supportResourceContent) {
		this.supportResourceContent = supportResourceContent;
	}

	@Column(columnDefinition = "TEXT")
	public String getProcessFlowContent() {
		return processFlowContent;
	}

	public void setProcessFlowContent(String processFlowContent) {
		this.processFlowContent = processFlowContent;
	}

	@Column(columnDefinition = "TEXT")
	public String getSourceListContent() {
		return sourceListContent;
	}

	public void setSourceListContent(String sourceListContent) {
		this.sourceListContent = sourceListContent;
	}

	public String getInvestigateEmployee() {
		return investigateEmployee;
	}

	public void setInvestigateEmployee(String investigateEmployee) {
		this.investigateEmployee = investigateEmployee;
	}

	public String getAttendEmployee() {
		return attendEmployee;
	}

	public void setAttendEmployee(String attendEmployee) {
		this.attendEmployee = attendEmployee;
	}

	@OneToOne
	@JoinColumn(name="fileGroupId")
	public FileGroup getLayoutFile() {
		return layoutFile;
	}

	public void setLayoutFile(FileGroup layoutFile) {
		this.layoutFile = layoutFile;
	}

}
