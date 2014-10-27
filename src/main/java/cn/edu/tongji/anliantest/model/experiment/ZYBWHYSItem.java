package cn.edu.tongji.anliantest.model.experiment;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * 《职业病危害因素》条目-实体类  
 */

@Entity
@Table(name="zybwhys_item")
public class ZYBWHYSItem implements Serializable{

	private static final long serialVersionUID = 3042779942528067140L;
	
	private Long id;
	
	private String englishName;		//英文名
	private String ChineseName;     //中文名
	private String CASNum;			//化学文摘号
	
	private BigDecimal MAC;
	private BigDecimal PC_TWA;
	private BigDecimal PC_STEL;
	private BigDecimal OM;			//超限倍数
	
	private String comment;			//备注

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
		return ChineseName;
	}

	public void setChineseName(String chineseName) {
		ChineseName = chineseName;
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
	
}
