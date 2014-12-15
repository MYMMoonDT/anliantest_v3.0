package cn.edu.tongji.anliantest.service;

import cn.edu.tongji.anliantest.model.GZRWDTable;
import cn.edu.tongji.anliantest.util.DataWrapper;

public interface GZRWDService {
	public DataWrapper<GZRWDTable> getGZRWDTableById(Long gzrwdTableId);
	
	public DataWrapper<GZRWDTable> getGZRWDTableByProject(Long projectId);
	
	public DataWrapper<GZRWDTable> addGZRWDTable(GZRWDTable gzrwdTable, Long taskId, Long employeeId);
	
	public DataWrapper<GZRWDTable> updateGZRWDTable(GZRWDTable gzrwdTable);
	
	public DataWrapper<Void> deleteGZRWDTable(Long gzrwdTableId);
	
}
