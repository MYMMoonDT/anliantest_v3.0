package cn.edu.tongji.anliantest.dao;

import cn.edu.tongji.anliantest.model.Log;

public interface LogDao {
	public Log getLogById(Long logId);
	
	public Long addLog(Log log);
	
	public void updateLog(Log log);
	
	public void deleteLog(Long logId);
}
