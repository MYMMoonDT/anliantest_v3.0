package cn.edu.tongji.anliantest.model.experiment;

import java.util.Date;

public class JCBGGroup {
	private String workshopPosition;   	//车间岗位
	private String zybwhysItem;         //检测项目
	private String zybwhysItemDetailName;         
	
	private Date[] testDate = new Date[3];
	private String[][] sampleNum = new String[3][20];
	private String[][] result = new String[3][20];
	private String[][] touchTime = new String[3][20];
	private Integer[][] collectTime = new Integer[3][20];
	
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
