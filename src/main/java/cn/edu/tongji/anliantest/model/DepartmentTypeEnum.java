package cn.edu.tongji.anliantest.model;

public enum DepartmentTypeEnum {
	MARKET,		//市场部
	EVALUATION, //评价部
	DETECTION,  //检测部
	ADMIN,		//行政部
	QUALITY,	//质控部
	MANAGER,    //总经理
	TEACHNICAL_DIRECTOR,	//技术负责人
	QUALITY_DIRECTOR;   	//质量负责人
	
	@Override
	public String toString() {
		String ret = "";
		if (this == DepartmentTypeEnum.MARKET) {
			ret = "市场部";
		} else if(this == DepartmentTypeEnum.EVALUATION) {
			ret = "评价部";
		} else if(this == DepartmentTypeEnum.DETECTION) {
			ret = "检测部";
		} else if(this == DepartmentTypeEnum.ADMIN) {
			ret = "行政部";
		} else if(this == DepartmentTypeEnum.QUALITY) {
			ret = "质控部";
		} else if(this == DepartmentTypeEnum.MANAGER) {
			ret = "总经理";
		} else if(this == DepartmentTypeEnum.TEACHNICAL_DIRECTOR) {
			ret = "技术负责人";
		} else if(this == DepartmentTypeEnum.QUALITY_DIRECTOR) {
			ret = "质量负责人";
		}
		return ret;
	}
}
