package cn.edu.tongji.anliantest.model.experiment;

public class JCBGSiO2Item {
	
	private String workshopPosition;
	private String sampleNum;
	private double result;
	
	public JCBGSiO2Item() {
	}

	public JCBGSiO2Item(String workshopPosition) {
		this.setWorkshopPosition(workshopPosition);
	}
	
	public String getSampleNum() {
		return sampleNum;
	}

	public void setSampleNum(String sampleNum) {
		this.sampleNum = sampleNum;
	}

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}
	
	public String getWorkshopPosition() {
		return workshopPosition;
	}

	public void setWorkshopPosition(String workshopPosition) {
		this.workshopPosition = workshopPosition;
	}
	
	@Override
	public boolean equals(Object obj) {
		JCBGSiO2Item item = (JCBGSiO2Item) obj;
		return workshopPosition.equals(item.getWorkshopPosition());
	}
}
