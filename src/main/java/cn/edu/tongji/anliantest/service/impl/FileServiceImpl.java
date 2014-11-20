package cn.edu.tongji.anliantest.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.tongji.anliantest.dao.FileGroupDao;
import cn.edu.tongji.anliantest.model.FileGroup;
import cn.edu.tongji.anliantest.service.FileService;
import cn.edu.tongji.anliantest.util.DataWrapper;

@Service("fileService")
public class FileServiceImpl implements FileService{
	private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
	
	@Autowired
	private FileGroupDao fileGroupDao;
	
	@Override
	public DataWrapper<FileGroup> getFileGroupById(Long fileGroupId) {
		return null;
	}

	@Override
	public DataWrapper<FileGroup> addFileGroup(FileGroup fileGroup) {
		DataWrapper<FileGroup> ret = new DataWrapper<FileGroup>();
		
		fileGroup.setUploadDate(new Date());
		
		fileGroupDao.addFileGroup(fileGroup);
		
		ret.setData(fileGroupDao.getFileGroupById(fileGroup.getId()));
		
		logger.info("添加资料信息:"+fileGroup.getGroupName()+"("+fileGroup.getId()+")");
		
		return ret;
	}

	@Override
	public DataWrapper<FileGroup> updateFileGroup(FileGroup fileGroup) {
		return null;
	}

	@Override
	public DataWrapper<Void> deleteFileGroup(Long fileGroupId) {
		DataWrapper<Void> ret = new DataWrapper<Void>();
		
		fileGroupDao.deleteFileGroup(fileGroupId);
		
		logger.info("删除资料信息:("+fileGroupId+")");
		
		return ret;
	}

	@Override
	public DataWrapper<List<FileGroup>> getFileGroupList(Long projectId, int currPageNum,
			int numPerPage) {
		return fileGroupDao.getFileGroupList(projectId, currPageNum, numPerPage);
	}
	
}
