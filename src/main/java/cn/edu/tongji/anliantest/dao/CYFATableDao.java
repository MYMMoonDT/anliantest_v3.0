package cn.edu.tongji.anliantest.dao;

import cn.edu.tongji.anliantest.model.experiment.CYFATable;

public interface CYFATableDao {
	public CYFATable getCYFATableById(Long cyfaTableId);
	
	public CYFATable getCYFATableByProjectId(Long projectId);
	
	public Long addCYFATable(CYFATable cyfaTable);
	
	public void updateCYFATable(CYFATable cyfaTable);
	
	public void deleteCYFATable(Long cyfaTableId);

}
