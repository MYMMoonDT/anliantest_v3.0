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

@Entity
@Table(name="file_item")

public class FileItem implements Serializable{


	private static final long serialVersionUID = -4590967191656569146L;
	
	private Long id;

	private String fileName;
	private String folderName;
	private String filePath;
	private Date uploadDate;
	private Project project;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getCreateDate() {
		return uploadDate;
	}

	public void setCreateDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	
	@ManyToOne
	@JoinColumn(name="projectId")
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	

}
