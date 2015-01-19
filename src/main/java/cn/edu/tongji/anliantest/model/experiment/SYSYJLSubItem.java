package cn.edu.tongji.anliantest.model.experiment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.edu.tongji.anliantest.model.Employee;

@Entity
@Table(name="sysyjl_sub_item")
public class SYSYJLSubItem implements Serializable{
	private static final long serialVersionUID = 6169983227377711258L;
	
	private Long id;
	
	private CYJCFFItem cyjcffItem;
	
    private String sampleNumBase;
    private Integer sampleCount;
    private Integer sampleNumStart;
    private Integer sampleNumEnd;

    private String sampleAtmos;
    private String sampleTemperature;
    private String sampleVolume;
    
    private Employee receiveEmployee;
    private Date receiveDate;
    
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
	public String getSampleNumBase() {
		return sampleNumBase;
	}
	public void setSampleNumBase(String sampleNumBase) {
		this.sampleNumBase = sampleNumBase;
	}
	public Integer getSampleCount() {
		return sampleCount;
	}
	public void setSampleCount(Integer sampleCount) {
		this.sampleCount = sampleCount;
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
	public String getSampleAtmos() {
		return sampleAtmos;
	}
	public void setSampleAtmos(String sampleAtmos) {
		this.sampleAtmos = sampleAtmos;
	}
	public String getSampleTemperature() {
		return sampleTemperature;
	}
	public void setSampleTemperature(String sampleTemperature) {
		this.sampleTemperature = sampleTemperature;
	}
	public String getSampleVolume() {
		return sampleVolume;
	}
	public void setSampleVolume(String sampleVolume) {
		this.sampleVolume = sampleVolume;
	}
	@OneToOne
	@JoinColumn(name="receiveEmployeeId")
	public Employee getReceiveEmployee() {
		return receiveEmployee;
	}
	public void setReceiveEmployee(Employee receiveEmployee) {
		this.receiveEmployee = receiveEmployee;
	}
	@Temporal(TemporalType.DATE)
	public Date getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}
}
