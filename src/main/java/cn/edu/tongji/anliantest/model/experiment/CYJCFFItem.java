package cn.edu.tongji.anliantest.model.experiment;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/*
 * 《采样及检测方法》条目-实体类  
 */

@Entity
@Table(name="cyjcff_item")
public class CYJCFFItem implements Serializable{

	private static final long serialVersionUID = -3436398800939161028L;

	private Long id;
	
	private Set<CYJCYJItem> sampleStandardList = new HashSet<CYJCYJItem>(0);  //采样及检测依据

	private String sampleInstrument; //采样仪器名称
	
	private String sampleFlowRate;   //采样流量
	private String sampleTime;     	 //采样时间
	private String sampleComment;    //采样备注
	private String sampleCollector;  //样品收集器
	private String sampleStorage;    //样品保存
	private String sampleStatus;     //样品状态
	private String sampleMethodAndPeriod;     //样品状态保存方式及期限
	
	private String comment;			 //备注
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getSampleInstrument() {
		return sampleInstrument;
	}

	public void setSampleInstrument(String sampleInstrument) {
		this.sampleInstrument = sampleInstrument;
	}

	public String getSampleFlowRate() {
		return sampleFlowRate;
	}

	public void setSampleFlowRate(String sampleFlowRate) {
		this.sampleFlowRate = sampleFlowRate;
	}

	public String getSampleTime() {
		return sampleTime;
	}

	public void setSampleTime(String sampleTime) {
		this.sampleTime = sampleTime;
	}

	public String getSampleCollector() {
		return sampleCollector;
	}

	public void setSampleCollector(String sampleCollector) {
		this.sampleCollector = sampleCollector;
	}

	public String getSampleStorage() {
		return sampleStorage;
	}

	public void setSampleStorage(String sampleStorage) {
		this.sampleStorage = sampleStorage;
	}

	public String getSampleStatus() {
		return sampleStatus;
	}

	public void setSampleStatus(String sampleStatus) {
		this.sampleStatus = sampleStatus;
	}

	public String getSampleMethodAndPeriod() {
		return sampleMethodAndPeriod;
	}

	public void setSampleMethodAndPeriod(String sampleMethodAndPeriod) {
		this.sampleMethodAndPeriod = sampleMethodAndPeriod;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@Cascade({CascadeType.REFRESH})
	@JoinTable(name="cyjcff_cyjcyj",
		joinColumns={@JoinColumn(name="cyjcffId")},
		inverseJoinColumns={@JoinColumn(name="cyjcyjId")}
	)
	public Set<CYJCYJItem> getSampleStandardList() {
		return sampleStandardList;
	}

	public void setSampleStandardList(Set<CYJCYJItem> sampleStandardList) {
		this.sampleStandardList = sampleStandardList;
	}

	public String getSampleComment() {
		return sampleComment;
	}

	public void setSampleComment(String sampleComment) {
		this.sampleComment = sampleComment;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
