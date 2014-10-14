package cn.edu.tongji.anliantest.model;

public enum ProjectTypeEnum {
	JSXMZYBWHKZXGPJ; //建设项目职业病危害控制效果评价
	
	@Override
	public String toString() {
		String ret = "";
		if (this == ProjectTypeEnum.JSXMZYBWHKZXGPJ) {
			ret = "建设项目职业病危害控制效果评价";
		}
		return ret;
	}
}
