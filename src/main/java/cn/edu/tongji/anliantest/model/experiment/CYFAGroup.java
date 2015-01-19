package cn.edu.tongji.anliantest.model.experiment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="cyfa_group")
public class CYFAGroup implements Serializable{
	private static final long serialVersionUID = -1933276453681167390L;
	
	private Long id;
	
	private String workshopPosition; 	//车间岗位
	
	private List<CYFAItem> items = new ArrayList<CYFAItem>(0);

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWorkshopPosition() {
		return workshopPosition;
	}

	public void setWorkshopPosition(String workshopPosition) {
		this.workshopPosition = workshopPosition;
	}


	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.DELETE})
	@JoinColumn(name="groupId")
	@Fetch(FetchMode.SUBSELECT)
	public List<CYFAItem> getItems() {
		return items;
	}

	public void setItems(List<CYFAItem> items) {
		this.items = items;
	}
	
}
