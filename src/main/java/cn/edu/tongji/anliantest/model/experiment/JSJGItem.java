package cn.edu.tongji.anliantest.model.experiment;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jsjg_item")
public class JSJGItem implements java.io.Serializable{

	private static final long serialVersionUID = -175749521512712670L;
	
	private Long id;
	
	private String sampleNum;        	//样品编号 
	
	private BigDecimal result;
	private Integer resultScale;
	private String resultType;
	
	private BigDecimal touchTime;       //接触时间
	private Integer touchTimeScale;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSampleNum() {
		return sampleNum;
	}

	public void setSampleNum(String sampleNum) {
		this.sampleNum = sampleNum;
	}

	public BigDecimal getResult() {
		return result;
	}

	public void setResult(BigDecimal result) {
		this.result = result;
	}

	public Integer getResultScale() {
		return resultScale;
	}

	public void setResultScale(Integer resultScale) {
		this.resultScale = resultScale;
	}

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	public BigDecimal getTouchTime() {
		return touchTime;
	}

	public void setTouchTime(BigDecimal touchTime) {
		this.touchTime = touchTime;
	}

	public Integer getTouchTimeScale() {
		return touchTimeScale;
	}

	public void setTouchTimeScale(Integer touchTimeScale) {
		this.touchTimeScale = touchTimeScale;
	}
	
}
