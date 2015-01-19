package cn.edu.tongji.anliantest.model.experiment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "jsjg_group")
public class JSJGGroup implements java.io.Serializable{

	private static final long serialVersionUID = 5717991445999124849L;

	private Long id;
	
	private ZYBWHYSItem zybwhysItem;    //检测项目
	
	private String zybwhysItemDetailName;
	
	private Date testDate;
	
	private String workshopPosition;   	//车间岗位
	
	private String result;   		//检测结果
	private String resultType;
	
	private BigDecimal MAC;
	private BigDecimal PC_TWA;
	private BigDecimal PC_STEL;
	private BigDecimal OM;			//超限倍数
	
	private Integer MAC_Scale;
	private Integer PC_TWA_Scale;
	private Integer PC_STEL_Scale;
	private Integer OM_Scale;
	
	private List<JSJGItem> items = new ArrayList<JSJGItem>(0);
	
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

	@Temporal(TemporalType.DATE)
	public Date getTestDate() {
		return testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	public String getWorkshopPosition() {
		return workshopPosition;
	}

	public void setWorkshopPosition(String workshopPosition) {
		this.workshopPosition = workshopPosition;
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

	@OrderBy
	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.DELETE})
	@JoinColumn(name="groupId")
	@Fetch(FetchMode.SUBSELECT)
	public List<JSJGItem> getItems() {
		return items;
	}

	public void setItems(List<JSJGItem> items) {
		this.items = items;
	}
	
}
