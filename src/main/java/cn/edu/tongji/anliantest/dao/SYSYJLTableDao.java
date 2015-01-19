package cn.edu.tongji.anliantest.dao;

import cn.edu.tongji.anliantest.model.experiment.SYSYJLTable;

public interface SYSYJLTableDao {
	public SYSYJLTable getSYSYJLTableById(Long sysyjlTableId);
	
	public SYSYJLTable getSYSYJLTableByProjectId(Long projectId);
	
	public Long addSYSYJLTable(SYSYJLTable sysyjlTable);
	
	public void updateSYSYJLTable(SYSYJLTable sysyjlTable);
	
	public void deleteSYSYJLTable(Long sysyjlTableId);
}
