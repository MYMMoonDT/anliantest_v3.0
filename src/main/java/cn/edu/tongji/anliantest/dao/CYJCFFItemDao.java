package cn.edu.tongji.anliantest.dao;

import cn.edu.tongji.anliantest.model.experiment.CYJCFFItem;

public interface CYJCFFItemDao {
	public CYJCFFItem getCYJCFFItemById(Long cyjcffItemId);
	
	public Long addCYJCFFItem(CYJCFFItem cyjcffItem);
	
	public void updateCYJCFFItem(CYJCFFItem cyjcffItem);
	
	public void deleteCYJCFFItem(Long cyjcffItemId);
}
