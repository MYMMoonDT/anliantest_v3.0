package cn.edu.tongji.anliantest.service;

import cn.edu.tongji.anliantest.util.DataWrapper;

import java.util.List;

import cn.edu.tongji.anliantest.model.FileItem;

public interface FileItemService {
	public DataWrapper<FileItem> getFileItemById(Long fileId);
	
	public DataWrapper<FileItem> addFileItem(FileItem file);
	public DataWrapper<Void> deleteFileItem(Long fileId);
	public DataWrapper<FileItem> updateFileItem(FileItem file);
	
	public DataWrapper<List<FileItem>> getFileListByProjectId(Long projectId);
	

}
