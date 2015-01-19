package cn.edu.tongji.anliantest.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cn.edu.tongji.anliantest.model.experiment.ZYBWHYSItem;

@Entity
@Table(name="jctzd_item")
public class JCTZDItem implements Serializable{
	
	private static final long serialVersionUID = 4137485459747357695L;
	
	private Long id;
	
	private ZYBWHYSItem zybwhysItem;       	//检测项目
	private String zybwhysItemDetailName; 
	private Integer sampleCount;    		//样品数量

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
	
}
