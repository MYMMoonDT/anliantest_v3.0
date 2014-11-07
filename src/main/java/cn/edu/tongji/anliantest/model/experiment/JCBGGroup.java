package cn.edu.tongji.anliantest.model.experiment;

import java.util.Date;

public class JCBGGroup {
	private String workshopPosition;   	//车间岗位
	private String zybwhysItem;         //检测项目
	private String zybwhysItemDetailName;         
	
	private Date[] testDate;
	private String[][] sampleNum;
	private String[][] result;
	private String[][] touchTime;
	private Integer[][] collectTime;
	
	public String getWorkshopPosition() {
		return workshopPosition;
	}
	public void setWorkshopPosition(String workshopPosition) {
		this.workshopPosition = workshopPosition;
	}
	public String getZybwhysItem() {
		return zybwhysItem;
	}
	public void setZybwhysItem(String zybwhysItem) {
		this.zybwhysItem = zybwhysItem;
	}
	public String[][] getSampleNum() {
		return sampleNum;
	}
	public void setSampleNum(String[][] sampleNum) {
		this.sampleNum = sampleNum;
	}
	public String[][] getResult() {
		return result;
	}
	public void setResult(String[][] result) {
		this.result = result;
	}
	public String[][] getTouchTime() {
		return touchTime;
	}
	public void setTouchTime(String[][] touchTime) {
		this.touchTime = touchTime;
	}
	public Integer[][] getCollectTime() {
		return collectTime;
	}
	public void setCollectTime(Integer[][] collectTime) {
		this.collectTime = collectTime;
	}
	public Date[] getTestDate() {
		return testDate;
	}
	public void setTestDate(Date[] testDate) {
		this.testDate = testDate;
	}
	public String getZybwhysItemDetailName() {
		return zybwhysItemDetailName;
	}
	public void setZybwhysItemDetailName(String zybwhysItemDetailName) {
		this.zybwhysItemDetailName = zybwhysItemDetailName;
	}
	
}
