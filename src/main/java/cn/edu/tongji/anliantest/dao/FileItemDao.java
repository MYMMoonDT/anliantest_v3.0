package cn.edu.tongji.anliantest.dao;

import java.util.List;

import cn.edu.tongji.anliantest.model.FileItem;

public interface FileItemDao {
	public FileItem getFileById(Long fileId);
	public List<FileItem> getFileByProjectId(Long projectId);
	public Long addFile(FileItem file);
	public void updateFile(FileItem file);
	public void deleteFile(Long fileId);
	
}
