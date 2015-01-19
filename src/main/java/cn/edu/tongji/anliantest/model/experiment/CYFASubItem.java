package cn.edu.tongji.anliantest.model.experiment;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cyfa_sub_item")
public class CYFASubItem implements Serializable{

	private static final long serialVersionUID = -2201993311362829968L;

	private Long id;
	
	private CYJCFFItem cyjcffItem;
	
	private Integer sampleCount;    	//样品数量
	
	private String sampleNumBase;       //样品编号 (项目类型)+(项目编号)+(车间岗位)
	private Integer sampleNumStart;
	private Integer sampleNumEnd;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="cyjcffItemId")
	public CYJCFFItem getCyjcffItem() {
		return cyjcffItem;
	}

	public void setCyjcffItem(CYJCFFItem cyjcffItem) {
		this.cyjcffItem = cyjcffItem;
	}

	public Integer getSampleCount() {
		return sampleCount;
	}

	public void setSampleCount(Integer sampleCount) {
		this.sampleCount = sampleCount;
	}

	public String getSampleNumBase() {
		return sampleNumBase;
	}

	public void setSampleNumBase(String sampleNumBase) {
		this.sampleNumBase = sampleNumBase;
	}

	public Integer getSampleNumStart() {
		return sampleNumStart;
	}

	public void setSampleNumStart(Integer sampleNumStart) {
		this.sampleNumStart = sampleNumStart;
	}

	public Integer getSampleNumEnd() {
		return sampleNumEnd;
	}

	public void setSampleNumEnd(Integer sampleNumEnd) {
		this.sampleNumEnd = sampleNumEnd;
	}
}
