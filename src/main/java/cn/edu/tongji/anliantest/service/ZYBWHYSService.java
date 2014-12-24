package cn.edu.tongji.anliantest.service;

import java.util.List;

import cn.edu.tongji.anliantest.model.experiment.ZYBWHYSItem;
import cn.edu.tongji.anliantest.util.DataWrapper;

public interface ZYBWHYSService {
	public DataWrapper<ZYBWHYSItem> getZYBWHYSItemById(Long zybwhysItemId);
	
	public DataWrapper<ZYBWHYSItem> addZYBWHYSItem(ZYBWHYSItem zybwhysItem);
	
	public DataWrapper<ZYBWHYSItem> updateZYBWHYSItem(ZYBWHYSItem zybwhysItem);
	
	public DataWrapper<Void> deleteZYBWHYSItem(Long zybwhysItemId);
	
	public DataWrapper<List<ZYBWHYSItem>> getAllZYBWHYSItem();
}
