package cn.edu.tongji.anliantest.dao;

import cn.edu.tongji.anliantest.model.GZRWDTable;

public interface GZRWDTableDao {
	public GZRWDTable getGZRWDById(Long gzrwdTableId);
	
	public GZRWDTable getGZRWDByProjectId(Long projectId);
	
	public Long addGZRWD(GZRWDTable gzrwdTable);
	
	public void updateGZRWD(GZRWDTable gzrwdTable);
	
	public void deleteGZRWD(Long gzrwdTableId);
}
