package cn.edu.tongji.anliantest.dao;

import cn.edu.tongji.anliantest.model.experiment.ZYBWHYSItem;

public interface ZYBWHYSItemDao {
	public ZYBWHYSItem getZYBWHYSItemById(Long zybwhysItemId);
	
	public ZYBWHYSItem getZYBWHYSItemByIName(String zybwhysItemName);
	
	public Long addZYBWHYSItem(ZYBWHYSItem zybwhysItem);
	
	public void updateZYBWHYSItem(ZYBWHYSItem zybwhysItem);
	
	public void deleteZYBWHYSItem(Long zybwhysItemId);
}
