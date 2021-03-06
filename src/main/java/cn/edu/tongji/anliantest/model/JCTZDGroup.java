package cn.edu.tongji.anliantest.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="jctzd_group")
public class JCTZDGroup implements Serializable{

	private static final long serialVersionUID = -7028345313920060163L;

	private Long id;
	
	private String workshopPosition; 	//车间岗位
	
	private List<JCTZDItem> items = new ArrayList<JCTZDItem>(0);

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

	@OrderBy
	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.DELETE})
	@JoinColumn(name="groupId")
	@Fetch(FetchMode.SUBSELECT)
	public List<JCTZDItem> getItems() {
		return items;
	}

	public void setItems(List<JCTZDItem> items) {
		this.items = items;
	}
}
