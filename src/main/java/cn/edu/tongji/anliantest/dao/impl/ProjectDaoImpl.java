package cn.edu.tongji.anliantest.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.edu.tongji.anliantest.dao.AbstractHibernateDao;
import cn.edu.tongji.anliantest.dao.ProjectDao;
import cn.edu.tongji.anliantest.model.Project;
import cn.edu.tongji.anliantest.util.DataWrapper;
import cn.edu.tongji.anliantest.util.PageResult;

@Repository
public class ProjectDaoImpl extends AbstractHibernateDao<Project, Long> implements ProjectDao{

	protected ProjectDaoImpl() {
		super(Project.class);
	}
	
	@Override
	public Project getProjectById(Long projectId) {
		return findById(projectId);
	}
	
	@Override
	public Long addProject(Project project) {
		return add(project);
	}


	@Override
	public void updateProject(Project project) {
		saveOrUpdate(project);
	}

	@Override
	public void deleteProject(Long projectId) {
		delete(findById(projectId));
	}

	@Override
	public DataWrapper<List<Project>> getProjectList(int currPageNum, int numPerPage) {
		DataWrapper<List<Project>> ret = new DataWrapper<List<Project>>();
		
		PageResult<Project> pageResult = findByCriteriaByPage(currPageNum, numPerPage);
		
		ret.setData(pageResult.getData());
		ret.setNumPerPage(pageResult.getNumPerPage());
		ret.setCurrPageNum(pageResult.getCurrPageNum());
		ret.setTotalItemNum(pageResult.getTotalItemNum());
		ret.setTotalPageNum(pageResult.getTotalPageNum());
		
		return ret;
	}
	
}
