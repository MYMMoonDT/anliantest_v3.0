package cn.edu.tongji.anliantest.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.tongji.anliantest.dao.FileGroupDao;
import cn.edu.tongji.anliantest.dao.ProjectDao;
import cn.edu.tongji.anliantest.model.FileGroup;
import cn.edu.tongji.anliantest.model.FileItem;
import cn.edu.tongji.anliantest.model.Project;
import cn.edu.tongji.anliantest.service.FileService;
import cn.edu.tongji.anliantest.util.ApplicationContextUtil;
import cn.edu.tongji.anliantest.util.DataWrapper;
import cn.edu.tongji.anliantest.util.FileUtil;

@Service("fileService")
public class FileServiceImpl implements FileService{
	private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
	
	@Autowired
	private FileGroupDao fileGroupDao;
	@Autowired
	private ProjectDao projectDao;
	
	@Override
	public DataWrapper<FileGroup> getFileGroupById(Long fileGroupId) {
		return null;
	}

	@Override
	public DataWrapper<FileGroup> addFileGroup(FileGroup fileGroup,
			FileItem[] items, List<MultipartFile> files) {
		DataWrapper<FileGroup> ret = new DataWrapper<FileGroup>();
		
		ServletContext context = ApplicationContextUtil.getContext().getServletContext();
		
		Project project = projectDao.getProjectById(fileGroup.getProject().getId());
		
		fileGroup.setUploadDate(new Date());
		
		for(int i = 0; i < files.size(); i++) {
			String filePath = "upload/" + project.getNumber() + "/" + fileGroup.getGroupName();
			String fileName =  items[i].getFileName();
			
		    FileUtil.saveFile(context.getRealPath(filePath), files.get(i), fileName);
			
			items[i].setFilePath(filePath);
			fileGroup.getItems().add(items[i]);
		}
		
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
		
		ServletContext context = ApplicationContextUtil.getContext().getServletContext();
		
		for(FileItem item : fileGroupDao.getFileGroupById(fileGroupId).getItems()) {
			String filePath = item.getFilePath();
			String fileName = item.getFileName();
			FileUtil.deleteFile(context.getRealPath(filePath), fileName);
		}
		
		fileGroupDao.deleteFileGroup(fileGroupId);
		
		logger.info("删除资料信息:("+fileGroupId+")");
		
		return ret;
	}

	@Override
	public DataWrapper<List<FileGroup>> getFileGroupList(Long projectId, int currPageNum,
			int numPerPage) {
		return fileGroupDao.getFileGroupList(projectId, currPageNum, numPerPage);
	}

	@Override
	public DataWrapper<List<FileGroup>> getAllFileGroupList(Long projectId) {
		return fileGroupDao.getFileGroupList(projectId);
	}
}
