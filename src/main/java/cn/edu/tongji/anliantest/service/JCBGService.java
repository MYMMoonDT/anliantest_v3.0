package cn.edu.tongji.anliantest.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import cn.edu.tongji.anliantest.model.experiment.JCBGTable;
import cn.edu.tongji.anliantest.model.experiment.JCBGTableInput;
import cn.edu.tongji.anliantest.util.DataWrapper;

public interface JCBGService {
	public DataWrapper<JCBGTable> getJCBGTableById(Long jcbgTableId);
	
	public DataWrapper<JCBGTable> getJCBGTableByProjectId(Long projectId);
	
	public File getJCBGFile(Long projectId);
	
	public DataWrapper<JCBGTable> addJCBGTable(Long taskId, Long employeeId, JCBGTableInput jcbgTable);
	
	public DataWrapper<JCBGTable> uploadJCBGTable(Long projectId, MultipartFile file);
	
	public DataWrapper<JCBGTable> inputJCBGTable(Long projectId, JCBGTableInput jcbgTable);
	
	public DataWrapper<JCBGTable> updateJCBGTable(JCBGTable jcbgTable);
	
	public DataWrapper<Void> deleteJCBGTable(Long jcbgTableId);
	
}
