package cn.edu.tongji.anliantest.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.tongji.anliantest.dao.ProjectDao;
import cn.edu.tongji.anliantest.model.Project;
import cn.edu.tongji.anliantest.service.ProjectService;
import cn.edu.tongji.anliantest.util.DataWrapper;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService{

	private static final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);
	
	@Autowired
    private ProjectDao projectDao;
	
	@Override
	public DataWrapper<Project> getProjectById(Long projectId) {
		DataWrapper<Project> ret = new DataWrapper<>();
        
		Project project = projectDao.getProjectById(projectId);
        ret.setData(project);
        
        return ret;
	}

	@Override
	public DataWrapper<Project> addProject(Project project) {
		DataWrapper<Project> ret = new DataWrapper<>();
		
		project.setCreateTime(new Date());
		projectDao.addProject(project);
		
		ret.setData(projectDao.getProjectById(project.getId()));
		
		logger.info("添加项目信息:"+project.getName()+"("+project.getId()+")");
		
		return ret;
	}

	@Override
	public DataWrapper<Project> updateProject(Project project) {
		DataWrapper<Project> ret = new DataWrapper<>();
		
		projectDao.updateProject(project);
		
		ret.setData(projectDao.getProjectById(project.getId()));
		
		logger.info("更新项目信息:"+project.getName()+"("+project.getId()+")");
		
		return ret;
	}

	@Override
	public DataWrapper<Void> deleteProject(Long projectId) {
		DataWrapper<Void> ret = new DataWrapper<>();
		
		projectDao.deleteProject(projectId);
		
		logger.info("删除项目信息:" + "(" + projectId + ")");
		
		return ret;
	}

	@Override
	public DataWrapper<List<Project>> getProjectList(int currPageNum, int numPerPage) {
		return projectDao.getProjectList(currPageNum, numPerPage);
	}
	
}
