package cn.edu.tongji.anliantest.dao;

import cn.edu.tongji.anliantest.model.PJFA;

public interface PJFADao {
	public PJFA getPJFAById(Long pjfaId);
	
	public PJFA getPJFAByProjectId(Long projectId);
	
	public Long addPJFA(PJFA pjfa);
	
	public void updatePJFA(PJFA pjfa);
	
	public void deletePJFA(Long pjfaId);
}
