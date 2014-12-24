package cn.edu.tongji.anliantest.service;

import cn.edu.tongji.anliantest.model.JCTZDTable;
import cn.edu.tongji.anliantest.util.DataWrapper;

public interface JCTZDService {
	public DataWrapper<JCTZDTable> getJCTZDTableById(Long jctzdTableId);
	
	public DataWrapper<JCTZDTable> getJCTZDTableProjectId(Long projectId);
	
	public DataWrapper<JCTZDTable> addJCTZDTable(JCTZDTable jctzdTable, Long taskId, Long employeeId);
	
	public DataWrapper<JCTZDTable> updateJCTZDTable(JCTZDTable jctzdTable);
	
	public DataWrapper<Void> deleteJCTZDTable(Long jctzdTableId);
}
