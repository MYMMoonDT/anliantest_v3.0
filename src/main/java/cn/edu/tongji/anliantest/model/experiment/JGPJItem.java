package cn.edu.tongji.anliantest.model.experiment;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "jgpj_item")
public class JGPJItem implements java.io.Serializable{

	private static final long serialVersionUID = 1972472776279227166L;

	private Long id;

	private ZYBWHYSItem zybwhysItem;    //检测项目
	private String zybwhysItemDetailName;
	
	private String workshopPosition;   	//车间岗位
	
	private Integer sampleCount;
	
	private BigDecimal touchTime;
	private Integer touchTimeScale;
	
	private BigDecimal MAC;
	private BigDecimal PC_TWA;
	private BigDecimal PC_STEL;
	private BigDecimal OM;			//超限倍数
	
	private Integer MAC_Scale;
	private Integer PC_TWA_Scale;
	private Integer PC_STEL_Scale;
	private Integer OM_Scale;
	
	private String result;
	private String resultType;
	private BigDecimal resultRangeStart;
	private BigDecimal resultRangeEnd;
	private Integer resultRangeScale;
	private String resultRangeStartType;
	private String resultRangeEndType;
	
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

	public String getWorkshopPosition() {
		return workshopPosition;
	}

	public void setWorkshopPosition(String workshopPosition) {
		this.workshopPosition = workshopPosition;
	}

	public Integer getSampleCount() {
		return sampleCount;
	}

	public void setSampleCount(Integer sampleCount) {
		this.sampleCount = sampleCount;
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

	public BigDecimal getMAC() {
		return MAC;
	}

	public void setMAC(BigDecimal mAC) {
		MAC = mAC;
	}

	public BigDecimal getPC_TWA() {
		return PC_TWA;
	}

	public void setPC_TWA(BigDecimal pC_TWA) {
		PC_TWA = pC_TWA;
	}

	public BigDecimal getPC_STEL() {
		return PC_STEL;
	}

	public void setPC_STEL(BigDecimal pC_STEL) {
		PC_STEL = pC_STEL;
	}

	public BigDecimal getOM() {
		return OM;
	}

	public void setOM(BigDecimal oM) {
		OM = oM;
	}

	public Integer getMAC_Scale() {
		return MAC_Scale;
	}

	public void setMAC_Scale(Integer mAC_Scale) {
		MAC_Scale = mAC_Scale;
	}

	public Integer getPC_TWA_Scale() {
		return PC_TWA_Scale;
	}

	public void setPC_TWA_Scale(Integer pC_TWA_Scale) {
		PC_TWA_Scale = pC_TWA_Scale;
	}

	public Integer getPC_STEL_Scale() {
		return PC_STEL_Scale;
	}

	public void setPC_STEL_Scale(Integer pC_STEL_Scale) {
		PC_STEL_Scale = pC_STEL_Scale;
	}

	public Integer getOM_Scale() {
		return OM_Scale;
	}

	public void setOM_Scale(Integer oM_Scale) {
		OM_Scale = oM_Scale;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	public BigDecimal getResultRangeStart() {
		return resultRangeStart;
	}

	public void setResultRangeStart(BigDecimal resultRangeStart) {
		this.resultRangeStart = resultRangeStart;
	}

	public BigDecimal getResultRangeEnd() {
		return resultRangeEnd;
	}

	public void setResultRangeEnd(BigDecimal resultRangeEnd) {
		this.resultRangeEnd = resultRangeEnd;
	}

	public Integer getResultRangeScale() {
		return resultRangeScale;
	}

	public void setResultRangeScale(Integer resultRangeScale) {
		this.resultRangeScale = resultRangeScale;
	}

	public String getResultRangeStartType() {
		return resultRangeStartType;
	}

	public void setResultRangeStartType(String resultRangeStartType) {
		this.resultRangeStartType = resultRangeStartType;
	}

	public String getResultRangeEndType() {
		return resultRangeEndType;
	}

	public void setResultRangeEndType(String resultRangeEndType) {
		this.resultRangeEndType = resultRangeEndType;
	}
	
	
}
