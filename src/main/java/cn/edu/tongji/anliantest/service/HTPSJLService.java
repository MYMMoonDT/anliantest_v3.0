package cn.edu.tongji.anliantest.service;

import cn.edu.tongji.anliantest.model.HTPSJLTable;
import cn.edu.tongji.anliantest.util.DataWrapper;

public interface HTPSJLService {
	public DataWrapper<HTPSJLTable> getHTPSJLTableById(Long htpsjlTableId);
	
	public DataWrapper<HTPSJLTable> addHTPSJLTable(HTPSJLTable htpsjlTable, Long employeeId);
	
	public DataWrapper<HTPSJLTable> updateHTPSJLTable(HTPSJLTable htpsjlTable);
	
	public DataWrapper<Void> deleteHTPSJLTable(Long htpsjlTableId);
}
