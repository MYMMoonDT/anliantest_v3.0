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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="sysyjl_item")
public class SYSYJLItem implements Serializable{
	private static final long serialVersionUID = -7991015381705376644L;
	
	private Long id;

	private ZYBWHYSItem zybwhysItem;
	private String zybwhysItemDetailName; 
    
	private Integer sampleCount;
	
	private List<SYSYJLSubItem> items = new ArrayList<SYSYJLSubItem>(0);
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToOne
	@JoinColumn(name="zybwhysItemId")
	public ZYBWHYSItem getZybwhysItem() {
		return zybwhysItem;
	}

	public void setZybwhysItem(ZYBWHYSItem zybwhysItem) {
		this.zybwhysItem = zybwhysItem;
	}
	
	public String getZybwhysItemDetailName() {
		return zybwhysItemDetailName;
	}

	public void setZybwhysItemDetailName(String zybwhysItemDetailName) {
		this.zybwhysItemDetailName = zybwhysItemDetailName;
	}

	public Integer getSampleCount() {
		return sampleCount;
	}

	public void setSampleCount(Integer sampleCount) {
		this.sampleCount = sampleCount;
	}

	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.DELETE})
	@JoinColumn(name="itemId")
	@Fetch(FetchMode.SUBSELECT)
	public List<SYSYJLSubItem> getItems() {
		return items;
	}

	public void setItems(List<SYSYJLSubItem> items) {
		this.items = items;
	}
}
