package cn.edu.tongji.anliantest.service;

import java.io.File;

import cn.edu.tongji.anliantest.model.experiment.SYSYJLTable;
import cn.edu.tongji.anliantest.util.DataWrapper;

public interface SYSYJLService {
	public DataWrapper<SYSYJLTable> getSYSYJLTableById(Long sysyjlTableId);
	
	public DataWrapper<SYSYJLTable> getSYSYJLTableByProjectId(Long projectId);
	
	public File getSYSYJLFile(Long projectId);
	
	public DataWrapper<SYSYJLTable> addSYSYJLTable(SYSYJLTable sysyjlTable, Long taskId, Long employeeId);
	
	public DataWrapper<SYSYJLTable> updateSYSYJLTable(SYSYJLTable sysyjlTable, Long taskId, Long employeeId);
	
	public DataWrapper<Void> deleteSYSYJLTable(Long sysyjlTableId);
}
