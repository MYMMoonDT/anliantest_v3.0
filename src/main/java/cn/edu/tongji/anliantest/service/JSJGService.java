package cn.edu.tongji.anliantest.service;

import cn.edu.tongji.anliantest.model.experiment.JSJGTable;
import cn.edu.tongji.anliantest.util.DataWrapper;

public interface JSJGService {
	public DataWrapper<JSJGTable> getJSJGTableById(Long jsjgTableId);
	
	public DataWrapper<JSJGTable> addJSJGTable(JSJGTable jsjgTable);
	
	public DataWrapper<JSJGTable> updateJSJGTable(JSJGTable jsjgTable);
	
	public DataWrapper<Void> deleteJSJGTable(Long jsjgTableId);
}
