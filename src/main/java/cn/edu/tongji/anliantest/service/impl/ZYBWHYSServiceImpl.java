package cn.edu.tongji.anliantest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.tongji.anliantest.dao.ZYBWHYSItemDao;
import cn.edu.tongji.anliantest.model.experiment.ZYBWHYSItem;
import cn.edu.tongji.anliantest.service.ZYBWHYSService;
import cn.edu.tongji.anliantest.util.DataWrapper;

@Service("zybwhysService")
public class ZYBWHYSServiceImpl implements ZYBWHYSService{

	@Autowired
	private ZYBWHYSItemDao zybwhysItemDao;
	
	@Override
	public DataWrapper<ZYBWHYSItem> getZYBWHYSItemById(Long zybwhysItemId) {
		DataWrapper<ZYBWHYSItem> ret= new DataWrapper<ZYBWHYSItem>();
		ret.setData(zybwhysItemDao.getZYBWHYSItemById(zybwhysItemId));
		return ret;
	}

	@Override
	public DataWrapper<ZYBWHYSItem> addZYBWHYSItem(ZYBWHYSItem zybwhysItem) {
		DataWrapper<ZYBWHYSItem> ret= new DataWrapper<ZYBWHYSItem>();
		zybwhysItemDao.addZYBWHYSItem(zybwhysItem);
		ret.setData(zybwhysItemDao.getZYBWHYSItemById(zybwhysItem.getId()));
		return ret;
	}

	@Override
	public DataWrapper<ZYBWHYSItem> updateZYBWHYSItem(ZYBWHYSItem zybwhysItem) {
		DataWrapper<ZYBWHYSItem> ret= new DataWrapper<ZYBWHYSItem>();
		zybwhysItemDao.updateZYBWHYSItem(zybwhysItem);
		ret.setData(zybwhysItemDao.getZYBWHYSItemById(zybwhysItem.getId()));
		return ret;
	}

	@Override
	public DataWrapper<Void> deleteZYBWHYSItem(Long zybwhysItemId) {
		DataWrapper<Void> ret= new DataWrapper<Void>();
		zybwhysItemDao.deleteZYBWHYSItem(zybwhysItemId);
		return ret;
	}

	@Override
	public DataWrapper<List<ZYBWHYSItem>> getAllZYBWHYSItem() {
		DataWrapper<List<ZYBWHYSItem>> ret = new DataWrapper<List<ZYBWHYSItem>>();
		
		ret.setData(zybwhysItemDao.getAllZYBWHYSItem());
		
		return ret;
	}
	
}
