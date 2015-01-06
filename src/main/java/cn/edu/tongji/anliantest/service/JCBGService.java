package cn.edu.tongji.anliantest.service;

import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

import cn.edu.tongji.anliantest.model.experiment.JCBGGroup;
import cn.edu.tongji.anliantest.model.experiment.JCBGTable;
import cn.edu.tongji.anliantest.model.experiment.JCBGTableInput;
import cn.edu.tongji.anliantest.util.DataWrapper;

public interface JCBGService {
	public DataWrapper<JCBGTable> getJCBGTableById(Long jcbgTableId);
	
	public DataWrapper<JCBGTable> addJCBGTable(Long projectId, ArrayList<JCBGGroup> list);
	
	public DataWrapper<JCBGTable> uploadJCBGTable(Long projectId, MultipartFile file);
	
	public DataWrapper<JCBGTable> inputJCBGTable(Long projectId, JCBGTableInput jcbgTable);
	
	public DataWrapper<JCBGTable> updateJCBGTable(JCBGTable jcbgTable);
	
	public DataWrapper<Void> deleteJCBGTable(Long jcbgTableId);
	
}
