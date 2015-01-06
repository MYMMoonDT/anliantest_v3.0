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
import javax.persistence.OneToMany;
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
	
	private Set<CYJCFFItem> items = new HashSet<CYJCFFItem>(0);		//采样及检测方法
	
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

	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.DELETE})
	@JoinColumn(name="zybwhysId")
	public Set<CYJCFFItem> getItems() {
		return items;
	}

	public void setItems(Set<CYJCFFItem> items) {
		this.items = items;
	}
}
