package cn.edu.tongji.anliantest.dao;

import cn.edu.tongji.anliantest.model.KHZLDJDTable;

public interface KHZLDJDTableDao {
	public KHZLDJDTable getKHZLDJDById(Long khzldjdTableId);
	
	public KHZLDJDTable getKHZLDJDByProjectId(Long projectId);
	
	public Long addKHZLDJD(KHZLDJDTable khzldjdTable);
	
	public void updateKHZLDJD(KHZLDJDTable khzldjdTable);
	
	public void deleteKHZLDJD(Long khzldjdTableId);
}
