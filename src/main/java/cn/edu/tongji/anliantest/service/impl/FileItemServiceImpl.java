package cn.edu.tongji.anliantest.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.tongji.anliantest.dao.FileItemDao;
import cn.edu.tongji.anliantest.model.FileItem;
import cn.edu.tongji.anliantest.service.FileItemService;
import cn.edu.tongji.anliantest.util.DataWrapper;


@Service("fileitemService")
public class FileItemServiceImpl  implements FileItemService{
	
	private static final Logger logger = LoggerFactory.getLogger(FileItemServiceImpl.class);
	
	@Autowired
	private FileItemDao fileItemDao;

	@Override
	public DataWrapper<FileItem> getFileItemById(Long fileId) {
		
		return getFileItemById(fileId);
	}
	
	@Override
	public DataWrapper<FileItem> addFileItem(FileItem file) {
		DataWrapper<FileItem> ret = new DataWrapper<>();
		
		fileItemDao.addFile(file);
		
		ret.setData(fileItemDao.getFileById(file.getId()));
		
		logger.info("添加文件信息:"+file.getId());
		
		return ret;
	}

	@Override
	public DataWrapper<Void> deleteFileItem(Long fileId) {
		DataWrapper<Void> ret = new DataWrapper<>();
		
		fileItemDao.deleteFile(fileId);
		
		logger.info("删除文件信息:"+fileId);
		
		return ret;		
	}

	@Override
	public DataWrapper<FileItem> updateFileItem(FileItem file) {
		DataWrapper<FileItem> ret = new DataWrapper<>();
		
		fileItemDao.updateFile(file);
		
		ret.setData(fileItemDao.getFileById(file.getId()));
		
		logger.info("更新文件信息:"+file.getId());
		
		return ret;
	}

	@Override
	public DataWrapper<List<FileItem>> getFileListByProjectId(Long projectId) {
		DataWrapper<List<FileItem>> ret = new DataWrapper<>();
		
		ret.setData(fileItemDao.getFileByProjectId(projectId));
		
		return ret;
	}

}
