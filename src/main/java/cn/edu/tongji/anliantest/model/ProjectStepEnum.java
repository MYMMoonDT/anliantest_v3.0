package cn.edu.tongji.anliantest.model;

public enum ProjectStepEnum {
	STEP1, //1.项目录入
	STEP2, //2.项目下达
	STEP3; //3.项目前期准备
	
	@Override
	public String toString() {
		String ret = "";
		
		if (this == ProjectStepEnum.STEP1) {
			ret = "1.项目录入";
		} else if(this == ProjectStepEnum.STEP2) {
			ret = "2.项目下达";
		} else if(this == ProjectStepEnum.STEP3) {
			ret = "3.项目前期准备";
		}
		
		return ret;
	}
}
