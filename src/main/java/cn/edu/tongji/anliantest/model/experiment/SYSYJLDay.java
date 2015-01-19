package cn.edu.tongji.anliantest.model.experiment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="sysyjl_day")
public class SYSYJLDay implements Serializable{
	private static final long serialVersionUID = -38343029714040478L;
	
	private Long id;
	
	private Date sampleDate;
	
	private List<SYSYJLGroup> items = new ArrayList<SYSYJLGroup>(0);

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Temporal(TemporalType.DATE)
	public Date getSampleDate() {
		return sampleDate;
	}

	public void setSampleDate(Date sampleDate) {
		this.sampleDate = sampleDate;
	}

	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.DELETE})
	@JoinColumn(name="dayId")
	@Fetch(FetchMode.SUBSELECT)
	public List<SYSYJLGroup> getItems() {
		return items;
	}

	public void setItems(List<SYSYJLGroup> items) {
		this.items = items;
	}
	
}
