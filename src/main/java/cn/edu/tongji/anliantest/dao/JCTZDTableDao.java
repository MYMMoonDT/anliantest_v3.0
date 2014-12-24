package cn.edu.tongji.anliantest.dao;

import cn.edu.tongji.anliantest.model.JCTZDTable;

public interface JCTZDTableDao {
	public JCTZDTable getJCTZDTableById(Long jctzdTableId);

	public JCTZDTable getJCTZDTableByProjectId(Long projectId);

	public Long addJCTZDTable(JCTZDTable jctzdTable);
	
	public void updateJCTZDTable(JCTZDTable jctzdTable);
	
	public void deleteJCTZDTable(Long jctzdTableId);
	
	
}
