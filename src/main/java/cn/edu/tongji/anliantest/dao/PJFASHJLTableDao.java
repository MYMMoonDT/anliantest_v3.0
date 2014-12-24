package cn.edu.tongji.anliantest.dao;

import cn.edu.tongji.anliantest.model.PJFASHJLTable;

public interface PJFASHJLTableDao {
	public PJFASHJLTable getPJFASHJLById(Long pjfashjlTableId);
	
	public PJFASHJLTable getPJFASHJLByProjectId(Long projectId);
	
	public Long addPJFASHJL(PJFASHJLTable pjfashjlTable);
	
	public void updatePJFASHJL(PJFASHJLTable pjfashjlTable);
	
	public void deletePJFASHJL(Long pjfashjlTableId);
}
