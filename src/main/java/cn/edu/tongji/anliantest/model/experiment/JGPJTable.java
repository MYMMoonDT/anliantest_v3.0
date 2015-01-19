package cn.edu.tongji.anliantest.model.experiment;

import java.util.ArrayList;
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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import cn.edu.tongji.anliantest.model.Project;

@Entity
@Table(name = "jgpj_table")
public class JGPJTable implements java.io.Serializable{

	private static final long serialVersionUID = 7867017219788613508L;

	private Long id;
	
	private Project project;
	
	private String tableNum;
	
	private List<JGPJItem> items = new ArrayList<JGPJItem>(0);

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

	@OrderBy
	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.DELETE})
	@JoinColumn(name="tableId")
	@Fetch(FetchMode.SUBSELECT)
	public List<JGPJItem> getItems() {
		return items;
	}

	public void setItems(List<JGPJItem> items) {
		this.items = items;
	}
}
