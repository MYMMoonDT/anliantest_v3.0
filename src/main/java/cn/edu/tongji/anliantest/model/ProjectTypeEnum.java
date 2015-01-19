package cn.edu.tongji.anliantest.model;

public enum ProjectTypeEnum {
	JSXMZYBWHKZXGPJ,   //建设项目职业病危害控制效果评价
	JSXMZYBWHYPJ,      //建设项目职业病危害预评价
	JSXMZYBWHXZPJ,     //建设项目职业病危害现状评价
	GGCSWSJCYPJ,       //公共场所卫生检测与评价
	GZCSZYBWHYSJCYPJ;  //工作场所职业病危害因素检测与评价
	
	@Override
	public String toString() {
		String ret = "";
		if (this == ProjectTypeEnum.JSXMZYBWHKZXGPJ) {
			ret = "建设项目职业病危害控制效果评价";
		} else if (this == ProjectTypeEnum.JSXMZYBWHYPJ) {
			ret = "建设项目职业病危害预评价";
		} else if (this == ProjectTypeEnum.JSXMZYBWHXZPJ) {
			ret = "建设项目职业病危害现状评价";
		} else if (this == ProjectTypeEnum.GGCSWSJCYPJ) {
			ret = "公共场所卫生检测与评价";
		} else if (this == ProjectTypeEnum.GZCSZYBWHYSJCYPJ) {
			ret = "工作场所职业病危害因素检测与评价";
		} 
		return ret;
	}
	
	public static String getShort(ProjectTypeEnum projectType) {
		String ret = "";
		
		switch(projectType) {
		case JSXMZYBWHKZXGPJ:
			ret = "KP";
			break;
		case JSXMZYBWHYPJ:
			ret = "YP";
			break;
		case JSXMZYBWHXZPJ:
			ret = "QT";
			break;
		case GGCSWSJCYPJ:
			ret = "GJ";
			break;
		case GZCSZYBWHYSJCYPJ:
			ret = "ZJ";
			break;
		}
		
		return ret;
	}
}
