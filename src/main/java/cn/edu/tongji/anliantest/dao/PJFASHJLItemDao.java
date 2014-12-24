package cn.edu.tongji.anliantest.dao;

import cn.edu.tongji.anliantest.model.PJFASHJLItem;

public interface PJFASHJLItemDao {
	public PJFASHJLItem getPJFASHJLById(Long pjfashjlItemId);
	
	public Long addPJFASHJL(PJFASHJLItem pjfashjlItem);
	
	public void updatePJFASHJL(PJFASHJLItem pjfashjlItem);
	
	public void deletePJFASHJL(Long pjfashjlItemId);
}
