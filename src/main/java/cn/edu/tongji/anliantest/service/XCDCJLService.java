package cn.edu.tongji.anliantest.service;

import cn.edu.tongji.anliantest.model.XCDCJLTable;
import cn.edu.tongji.anliantest.util.DataWrapper;

public interface XCDCJLService {
	
	public DataWrapper<XCDCJLTable> getXCDCJLById(Long xcdcjlTableId);
	
	public DataWrapper<XCDCJLTable> getXCDCJLByProject(Long projectId);
	
	public DataWrapper<XCDCJLTable> addXCDCJL(XCDCJLTable xcdcjlTable, Long taskId, Long employeeId);
	
	public DataWrapper<XCDCJLTable> updateXCDCJL(XCDCJLTable xcdcjlTable);
	
	public DataWrapper<Void> deleteXCDCJL(Long xcdcjlTableId);
	
	
}
