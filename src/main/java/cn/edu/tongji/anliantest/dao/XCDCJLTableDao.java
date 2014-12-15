package cn.edu.tongji.anliantest.dao;

import cn.edu.tongji.anliantest.model.XCDCJLTable;

public interface XCDCJLTableDao {
	public XCDCJLTable getXCDCJLById(Long xcdcjlTableId);
	
	public Long addXCDCJL(XCDCJLTable xcdcjlTable);
	
	public void updateXCDCJL(XCDCJLTable xcdcjlTable);
	
	public void deleteXCDCJL(Long xcdcjlTableId);
}
