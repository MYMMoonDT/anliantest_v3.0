package cn.edu.tongji.anliantest.dao;

import java.util.List;

import cn.edu.tongji.anliantest.model.Project;
import cn.edu.tongji.anliantest.util.DataWrapper;

public interface ProjectDao {
	public Project getProjectById(Long projectId);
	
	public Long addProject(Project project);
	
	public void updateProject(Project project);
	
	public void deleteProject(Long projectId);
	
	public DataWrapper<List<Project>> getProjectList(int currPageNum, int numPerPage);
}
