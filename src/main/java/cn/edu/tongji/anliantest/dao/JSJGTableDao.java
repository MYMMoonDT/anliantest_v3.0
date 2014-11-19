package cn.edu.tongji.anliantest.dao;

import cn.edu.tongji.anliantest.model.experiment.JSJGTable;

public interface JSJGTableDao {
	public JSJGTable getJSJGTableById(Long jsjgTableId);
	
	public Long addJSJGTable(JSJGTable jsjgTable);
	
	public void updateJSJGTable(JSJGTable jsjgTable);
	
	public void deleteJSJGTable(Long jsjgTableId);
	
	public JSJGTable getJSJGTableByProjectId(Long projectId);
}
