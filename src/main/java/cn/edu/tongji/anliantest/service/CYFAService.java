package cn.edu.tongji.anliantest.service;

import java.io.File;

import cn.edu.tongji.anliantest.model.experiment.CYFATable;
import cn.edu.tongji.anliantest.util.DataWrapper;

public interface CYFAService {
	public DataWrapper<CYFATable> getCYFATableById(Long cyfaTableId);
	
	public DataWrapper<CYFATable> getCYFATableProjectId(Long projectId);
	
	public File getCYFAFile(Long projectId);
	
	public DataWrapper<CYFATable> addCYFATable(CYFATable cyfaTable, Long taskId, Long employeeId);
	
	public DataWrapper<CYFATable> updateCYFATable(CYFATable cyfaTable);
	
	public DataWrapper<Void> deleteCYFATable(Long cyfaTableId);
	
	public DataWrapper<CYFATable> confirmCYFATable(CYFATable cyfaTable, Long taskId, Long employeeId);
}
