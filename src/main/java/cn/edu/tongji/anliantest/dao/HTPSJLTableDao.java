package cn.edu.tongji.anliantest.dao;

import cn.edu.tongji.anliantest.model.HTPSJLTable;

public interface HTPSJLTableDao {
	public HTPSJLTable getHTPSJLById(Long htpsjlTableId);
	
	public HTPSJLTable getHTPSJLByProjectId(Long projectId);
	
	public Long addHTPSJL(HTPSJLTable htpsjlTable);
	
	public void updateHTPSJL(HTPSJLTable htpsjlTable);
	
	public void deleteHTPSJL(Long htpsjlTableId);
}
