package cn.edu.tongji.anliantest.model.experiment;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * 《职业病危害因素》条目-实体类  
 */

@Entity
@Table(name="zybwhys_item")
public class ZYBWHYSItem implements Serializable{

	private static final long serialVersionUID = 3042779942528067140L;
	
	private Long id;
	
	private String englishName;		//英文名
	private String chineseName;     //中文名
	private String CASNum;			//化学文摘号
	
	private Set<CYJCYJItem> sampleStandardList = new HashSet<CYJCYJItem>(0);  //采样及检测依据

	private String sampleInstrument; //采样仪器名称
	
	private String sampleFlowRate;   //采样流量
	private String sampleTime;     	 //采样时间
	private String sampleComment;    //采样备注
	private String sampleCollector;  //样品收集器
	private String sampleStorage;    //样品保存
	private String sampleStatus;     //样品状态
	private String sampleMethodAndPeriod;     //样品状态保存方式及期限
	
	private BigDecimal MAC;
	private BigDecimal PC_TWA;
	private BigDecimal PC_STEL;
	private BigDecimal OM;			//超限倍数
	
	private String comment;			//备注

	private Integer MAC_Scale;
	private Integer PC_TWA_Scale;
	private Integer PC_STEL_Scale;
	private Integer OM_Scale;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public String getCASNum() {
		return CASNum;
	}

	public void setCASNum(String cASNum) {
		CASNum = cASNum;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE})
	@JoinTable(name="zybwhys_cyjcyj",
		joinColumns={@JoinColumn(name="zybwhysId")},
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
}
