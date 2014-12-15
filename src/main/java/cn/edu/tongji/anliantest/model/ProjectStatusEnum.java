package cn.edu.tongji.anliantest.model;

public enum ProjectStatusEnum {
	CREATE_HTPSJL, 	//创建合同评审记录
	SIGN_HTPSJL,   	//合同评审记录签字
	CREATE_GZRWD,  	//创建工作任务单
	APPOINT_XMFZR, 	//指定项目负责人
	CREATE_KHZLDJD,	//创建客户资料登记单
	CREATE_XCDCJL, 	//创建现场调查记录
	UPLOAD_PJFA; 	//上传评价方案
	
	@Override
	public String toString() {
		String ret = "";
		
		if (this == ProjectStatusEnum.CREATE_GZRWD) {
			ret = "创建合同评审记录";
		} else if(this == ProjectStatusEnum.SIGN_HTPSJL) {
			ret = "合同评审记录签字";
		} else if(this == ProjectStatusEnum.CREATE_GZRWD) {
			ret = "创建工作任务单";
		} else if(this == ProjectStatusEnum.APPOINT_XMFZR) {
			ret = "指定项目负责人";
		} else if(this == ProjectStatusEnum.CREATE_KHZLDJD) {
			ret = "创建客户资料登记单";
		} else if(this == ProjectStatusEnum.CREATE_XCDCJL) {
			ret = "创建现场调查记录";
		} else if(this == ProjectStatusEnum.UPLOAD_PJFA) {
			ret = "上传评价方案";
		}
		
		return ret;
	}
}
