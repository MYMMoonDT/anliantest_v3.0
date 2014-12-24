package cn.edu.tongji.anliantest.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/*
 *	《评价方案》- 实体类 
 */

@Entity
@Table(name="pjfa")
public class PJFA implements Serializable{

	private static final long serialVersionUID = 2013762796513536608L;
	
	private Long id;
	
	private Project project;
	
	private FileGroup pjfaFile;
	
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
	
	@OneToOne
	@JoinColumn(name="fileGroupId")
	public FileGroup getPjfaFile() {
		return pjfaFile;
	}

	public void setPjfaFile(FileGroup pjfaFile) {
		this.pjfaFile = pjfaFile;
	}
}
