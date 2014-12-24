package cn.edu.tongji.anliantest.service;

import cn.edu.tongji.anliantest.model.PJFASHJLItem;
import cn.edu.tongji.anliantest.model.PJFASHJLTable;
import cn.edu.tongji.anliantest.util.DataWrapper;

public interface PJFASHJLService {
	public DataWrapper<PJFASHJLTable> getPJFASHJLById(Long pjfashjlTableId);
	
	public DataWrapper<PJFASHJLTable> getPJFASHJLByProjectId(Long projectId);
	
	public DataWrapper<PJFASHJLTable> addPJFASHJL(PJFASHJLTable pjfashjlTable, Long taskId, Long employeeId);
	
	public DataWrapper<Void> signPJFASHJL(PJFASHJLItem pjfashjlItem, Long taskId, Long employeeId);
	
	public DataWrapper<PJFASHJLTable> updatePJFASHJL(PJFASHJLTable pjfashjlTable);
	
	public DataWrapper<PJFASHJLTable> deletePJFASHJL(Long pjfashjlTableId);
}
