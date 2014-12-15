package cn.edu.tongji.anliantest.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.edu.tongji.anliantest.model.FileGroup;
import cn.edu.tongji.anliantest.model.FileItem;
import cn.edu.tongji.anliantest.util.DataWrapper;

public interface FileService {
	public DataWrapper<FileGroup> getFileGroupById(Long fileGroupId);
	
	public DataWrapper<FileGroup> addFileGroup(FileGroup fileGroup, FileItem[] items, List<MultipartFile> files);
	
	public DataWrapper<FileGroup> updateFileGroup(FileGroup fileGroup);
	
	public DataWrapper<Void> deleteFileGroup(Long fileGroupId);
	
	public DataWrapper<List<FileGroup>> getFileGroupList(Long projectId, int currPageNum, int numPerPage);

	public DataWrapper<List<FileGroup>> getAllFileGroupList(Long projectId);
}
