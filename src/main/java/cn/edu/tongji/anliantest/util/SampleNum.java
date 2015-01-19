package cn.edu.tongji.anliantest.util;

public class SampleNum {
	public static String getSampleNumBaseFromProjectNum(String projectNum){
		String ret = "";
		
		String[] strArr = projectNum.split("-");
		
		if(strArr.length == 3) {
			String projectYear = strArr[0];
			String projectType = strArr[1];
			String projectOrder = strArr[2];
			
			ret = projectType + projectYear.substring(2) + projectOrder;
		}
		
		return ret;
	}
}
