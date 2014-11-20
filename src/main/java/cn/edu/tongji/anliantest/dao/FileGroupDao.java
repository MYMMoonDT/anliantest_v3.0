package cn.edu.tongji.anliantest.dao;

import java.util.List;

import cn.edu.tongji.anliantest.model.FileGroup;
import cn.edu.tongji.anliantest.util.DataWrapper;

public interface FileGroupDao {
	public FileGroup getFileGroupById(Long fileGroupId);
	
	public Long addFileGroup(FileGroup fileGroup);
	
	public void updateFileGroup(FileGroup fileGroup);
	
	public void deleteFileGroup(Long fileGroupId);
	
	public DataWrapper<List<FileGroup>> getFileGroupList(Long projectId, int currPageNum, int numPerPage);
}
