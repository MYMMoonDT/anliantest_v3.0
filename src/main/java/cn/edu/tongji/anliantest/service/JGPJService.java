package cn.edu.tongji.anliantest.service;

import cn.edu.tongji.anliantest.model.experiment.JGPJTable;
import cn.edu.tongji.anliantest.util.DataWrapper;

public interface JGPJService {
	public DataWrapper<JGPJTable> getJGPJTableById(Long jgpjTableId);
	
	public DataWrapper<JGPJTable> addJGPJTable(JGPJTable jgpjTable);
	
	public DataWrapper<JGPJTable> updateJGPJTable(JGPJTable jgpjTable);
	
	public DataWrapper<Void> deleteJGPJTable(Long jgpjTableId);
}
