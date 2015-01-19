package cn.edu.tongji.anliantest.model;

public enum ProjectStatusEnum {
	CREATE_HTPSJL, 		//创建合同评审记录
	SIGN_HTPSJL,   		//合同评审记录签字
	CREATE_GZRWD,  		//创建工作任务单
	APPOINT_XMFZR, 		//指定项目负责人
	CREATE_KHZLDJD,		//创建客户资料登记单
	CREATE_XCDCJL, 		//创建现场调查记录
	UPLOAD_PJFA, 		//上传评价方案
	CREATE_PJFASHJL, 	//创建评价方案审核记录
	SIGN_PJFASHJL, 	    //评价方案审核记录签字
	CREATE_JCTZD, 	    //创建检测通知单
	CONFIRM_CYFA, 	    //确认采样方案
	CREATE_SYSYJL, 	    //创建送样收样记录
	CONFIRM_SYSYJL, 	//确认送样收样记录
	INPUT_JCBG, 	    //输入检测报告
	COMPUTE_JCBG; 	    //计算检测报告
	
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
		} else if(this == ProjectStatusEnum.CREATE_PJFASHJL) {
			ret = "创建评价方案审核记录";
		} else if(this == ProjectStatusEnum.SIGN_PJFASHJL) {
			ret = "评价方案审核记录签字";
		} else if(this == ProjectStatusEnum.CREATE_JCTZD) {
			ret = "创建检测通知单";
		} else if(this == ProjectStatusEnum.CREATE_SYSYJL) {
			ret = "创建送样收样记录";
		} else if(this == ProjectStatusEnum.INPUT_JCBG) {
			ret = "输入检测报告";
		} else if(this == ProjectStatusEnum.COMPUTE_JCBG) {
			ret = "计算检测报告";
		}
		
		return ret;
	}
}
