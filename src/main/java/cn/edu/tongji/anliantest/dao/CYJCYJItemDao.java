package cn.edu.tongji.anliantest.dao;

import cn.edu.tongji.anliantest.model.experiment.CYJCYJItem;

public interface CYJCYJItemDao {
	public CYJCYJItem getCYJCYJItemById(Long cyjcyjItemId);
	
	public CYJCYJItem getCYJCYJItemByName(String cyjcyjItemName);
	
	public Long addCYJCYJItem(CYJCYJItem cyjcyjItem);
	
	public void updateCYJCYJItem(CYJCYJItem cyjcyjItem);
	
	public void deleteCYJCYJItem(Long cyjcyjItemId);
}
