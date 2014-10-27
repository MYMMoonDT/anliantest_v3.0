package cn.edu.tongji.anliantest.service;

import java.util.Date;
import java.util.List;

import cn.edu.tongji.anliantest.model.Project;
import cn.edu.tongji.anliantest.model.ProjectTypeEnum;
import cn.edu.tongji.anliantest.util.DataWrapper;

public interface ProjectService {
	public DataWrapper<Project> getProjectById(Long projectId);
	
	public DataWrapper<Project> addProject(Project project);
	
	public DataWrapper<Project> updateProject(Project project);
	
	public DataWrapper<Void> deleteProject(Long projectId);
	
	public DataWrapper<List<Project>> getProjectList(int currPageNum, int numPerPage);

	public DataWrapper<Void> appointProjectEmployee(Long taskId, Long employeeId, Long appointEmployeeId);

	public DataWrapper<Project> generateProjectNumber(Date createDate, ProjectTypeEnum projectType);
}
