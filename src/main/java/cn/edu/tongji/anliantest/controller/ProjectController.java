package cn.edu.tongji.anliantest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.tongji.anliantest.model.Project;
import cn.edu.tongji.anliantest.service.ProjectService;
import cn.edu.tongji.anliantest.util.DataWrapper;

@Controller
@RequestMapping("api")
public class ProjectController {
	ProjectService projectService;
	
	@Autowired
	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	@RequestMapping(value="project", method=RequestMethod.GET)
	@ResponseBody
	public DataWrapper<List<Project>> getProjectList(
		@RequestParam(value = "currPageNum") int currPageNum,
		@RequestParam(value = "numPerPage") int numPerPage) {
		DataWrapper<List<Project>> projectList = projectService.getProjectList(currPageNum, numPerPage);
		return projectList;
	}
	
	@RequestMapping(value="project/{projectId}", method=RequestMethod.GET)
    @ResponseBody
    public DataWrapper<Project> getProjectById(
		@PathVariable("projectId") Long projectId) {
		DataWrapper<Project> project = projectService.getProjectById(projectId);
		return project;
	}
	
	@RequestMapping(value="project", method=RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Project> addProject(
		@RequestBody Project project) {
		return projectService.addProject(project);
	}
	
	@RequestMapping(value="project", method=RequestMethod.PUT)
	@ResponseBody
	public DataWrapper<Project> updateProject(
		@RequestBody Project project) {
		return projectService.updateProject(project);
	}
	
	@RequestMapping(value="project/{projectId}", method=RequestMethod.DELETE)
	@ResponseBody
	public DataWrapper<Void> deleteProject(
			@PathVariable("projectId") Long projectId) {
		return projectService.deleteProject(projectId);
	}
	
	//指定项目负责人
	@RequestMapping(value="project/appoint", method=RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> appointProjectEmployee(
			@RequestParam("taskId") Long taskId,
			@RequestParam("employeeId") Long employeeId,
			@RequestParam("appointEmployeeId") Long appointEmployeeId) {
		return projectService.appointProjectEmployee(taskId, employeeId, appointEmployeeId);
	}
	
	//获取新建项目编号
	@RequestMapping(value="project/number", method=RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Project> generateProjectNum(
			@RequestBody Project project) {
		return projectService.generateProjectNumber(project.getCreateDate(), project.getType());
	}
}
