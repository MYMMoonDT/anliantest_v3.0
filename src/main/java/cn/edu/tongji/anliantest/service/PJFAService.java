package cn.edu.tongji.anliantest.service;

import cn.edu.tongji.anliantest.model.PJFA;
import cn.edu.tongji.anliantest.util.DataWrapper;

public interface PJFAService {
	public DataWrapper<PJFA> getPJFAById(Long pjfaId);
	
	public DataWrapper<PJFA> getPJFAByProjectId(Long projectId);
	
	public DataWrapper<PJFA> addPJFA(PJFA pjfa, Long taskId, Long employeeId);
	
	public DataWrapper<PJFA> updatePJFA(PJFA pjfa);
	
	public DataWrapper<Void> deletePJFA(Long pjfaId);
}
