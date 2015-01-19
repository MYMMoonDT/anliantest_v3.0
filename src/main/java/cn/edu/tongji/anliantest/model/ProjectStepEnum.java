package cn.edu.tongji.anliantest.model;

public enum ProjectStepEnum {
	STEP1, //1.项目录入
	STEP2, //2.项目下达
	STEP3, //3.项目前期准备
	STEP4, //4.项目检测环节
	STEP5, //5.项目实验环节
	STEP6; //6.项目数据处理
	
	@Override
	public String toString() {
		String ret = "";
		
		if (this == ProjectStepEnum.STEP1) {
			ret = "1.项目录入";
		} else if(this == ProjectStepEnum.STEP2) {
			ret = "2.项目下达";
		} else if(this == ProjectStepEnum.STEP3) {
			ret = "3.项目前期准备";
		} else if(this == ProjectStepEnum.STEP4) {
			ret = "4.项目检测环节";
		} else if(this == ProjectStepEnum.STEP5) {
			ret = "5.项目实验环节";
		}
		
		return ret;
	}
}
