package cn.edu.tongji.anliantest.dao;

import cn.edu.tongji.anliantest.model.experiment.JGPJTable;

public interface JGPJTableDao {
	public JGPJTable getJGPJTableById(Long jgpjTableId);
	
	public Long addJGPJTable(JGPJTable jgpjTable);
	
	public void updateJGPJTable(JGPJTable jgpjTable);
	
	public void deleteJGPJTable(Long jgpjTableId);
	
	public JGPJTable getJGPJTableByProjectId(Long projectId);
}
