package cn.edu.tongji.anliantest.service;

import java.util.List;

import cn.edu.tongji.anliantest.model.FileGroup;
import cn.edu.tongji.anliantest.util.DataWrapper;

public interface FileService {
	public DataWrapper<FileGroup> getFileGroupById(Long fileGroupId);
	
	public DataWrapper<FileGroup> addFileGroup(FileGroup fileGroup);
	
	public DataWrapper<FileGroup> updateFileGroup(FileGroup fileGroup);
	
	public DataWrapper<Void> deleteFileGroup(Long fileGroupId);
	
	public DataWrapper<List<FileGroup>> getFileGroupList(Long projectId, int currPageNum, int numPerPage);

}
