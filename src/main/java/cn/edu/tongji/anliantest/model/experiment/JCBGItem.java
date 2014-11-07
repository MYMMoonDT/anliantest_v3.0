package cn.edu.tongji.anliantest.model.experiment;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "jcbg_item")
public class JCBGItem implements java.io.Serializable{

	private static final long serialVersionUID = -847719545841146352L;
	
	private Long id;
	
	private ZYBWHYSItem zybwhysItem;    //检测项目
	
	private String workshopPosition;   	//车间岗位
	
	private String sampleNum;        	//样品编号
	
	private BigDecimal result;   		//检测结果
	private Integer resultScale;
	private String resultType;
	
	private Date testDate;
	private BigDecimal touchTime;       //接触时间
	private Integer touchTimeScale;     
	private Integer collectTime;		//采集时间
	
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
	public String getWorkshopPosition() {
		return workshopPosition;
	}
	public void setWorkshopPosition(String workshopPosition) {
		this.workshopPosition = workshopPosition;
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
	public Integer getCollectTime() {
		return collectTime;
	}
	public void setCollectTime(Integer collectTime) {
		this.collectTime = collectTime;
	}
	
	@Temporal(TemporalType.DATE)
	public Date gettestDate() {
		return testDate;
	}
	public void settestDate(Date testDate) {
		this.testDate = testDate;
	}
}
