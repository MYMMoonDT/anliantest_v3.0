package cn.edu.tongji.anliantest.service;

import java.io.File;

import cn.edu.tongji.anliantest.model.experiment.JGPJTable;
import cn.edu.tongji.anliantest.util.DataWrapper;

public interface JGPJService {
	public DataWrapper<JGPJTable> getJGPJTableById(Long jgpjTableId);
	
	public DataWrapper<JGPJTable> getJGPJTableByProjectId(Long projectId);
	
	public DataWrapper<JGPJTable> addJGPJTable(JGPJTable jgpjTable);
	
	public DataWrapper<JGPJTable> updateJGPJTable(JGPJTable jgpjTable);
	
	public DataWrapper<Void> deleteJGPJTable(Long jgpjTableId);
	
	public File getJGPJFile(Long projectId);
	
	public File getJGPJTmpFile(Long projectId);
}
