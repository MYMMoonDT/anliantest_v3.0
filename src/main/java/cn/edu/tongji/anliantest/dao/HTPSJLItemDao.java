package cn.edu.tongji.anliantest.dao;

import cn.edu.tongji.anliantest.model.HTPSJLItem;

public interface HTPSJLItemDao {
	public HTPSJLItem getHTPSJLById(Long htpsjlItemId);
	
	public Long addHTPSJL(HTPSJLItem htpsjlItem);
	
	public void updateHTPSJL(HTPSJLItem htpsjlItem);
	
	public void deleteHTPSJL(Long htpsjlItemId);
}
