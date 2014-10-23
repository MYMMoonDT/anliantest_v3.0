package cn.edu.tongji.anliantest.service;

import cn.edu.tongji.anliantest.model.KHZLDJDTable;
import cn.edu.tongji.anliantest.util.DataWrapper;

public interface KHZLDJDService {
	public DataWrapper<KHZLDJDTable> getKHZLDJDById(Long khzldjdTableId);
	
	public DataWrapper<KHZLDJDTable> addKHZLDJD(KHZLDJDTable khzldjdTable, Long taskId, Long employeeId);
	
	public DataWrapper<KHZLDJDTable> updateKHZLDJD(KHZLDJDTable khzldjdTable);
	
	public DataWrapper<Void> deleteKHZLDJD(Long khzldjdTableId);
}
