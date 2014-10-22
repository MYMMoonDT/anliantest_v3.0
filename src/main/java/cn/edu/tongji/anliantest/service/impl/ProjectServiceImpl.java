package cn.edu.tongji.anliantest.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.tongji.anliantest.dao.EmployeeDao;
import cn.edu.tongji.anliantest.dao.LogDao;
import cn.edu.tongji.anliantest.dao.ProjectDao;
import cn.edu.tongji.anliantest.dao.TaskDao;
import cn.edu.tongji.anliantest.model.Employee;
import cn.edu.tongji.anliantest.model.Log;
import cn.edu.tongji.anliantest.model.Project;
import cn.edu.tongji.anliantest.model.ProjectStatusEnum;
import cn.edu.tongji.anliantest.model.ProjectStepEnum;
import cn.edu.tongji.anliantest.model.Task;
import cn.edu.tongji.anliantest.service.ProjectService;
import cn.edu.tongji.anliantest.util.DataWrapper;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService{

	private static final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);
	
	@Autowired
    private ProjectDao projectDao;
	
	@Autowired
	private TaskDao taskDao;
	@Autowired
	private LogDao logDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
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
		project.setStep(ProjectStepEnum.STEP1);
		project.setStatus(ProjectStatusEnum.CREATE_HTPSJL);
		
		projectDao.addProject(project);
		
		//为项目业务负责人创建新建合同评审记录的Task
		Task task = new Task(project, 
				ProjectStepEnum.STEP1,
				ProjectStatusEnum.CREATE_HTPSJL, 
				project.getBusinessEmployee());
		taskDao.addTask(task);
		
		
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

	@Override
	public DataWrapper<Void> appointProjectEmployee(Long taskId, Long employeeId, Long appointEmployeeId) {
		DataWrapper<Void> ret = new DataWrapper<>();
		
		Task appointTask = taskDao.getTaskById(taskId);
		Project project = appointTask.getProject();
		Employee employee = employeeDao.getEmployeeById(employeeId);
		Employee appointEmployee = employeeDao.getEmployeeById(appointEmployeeId);
		
		//完成指定项目负责人的Task
		project.setProjectEmployee(appointEmployee);
		projectDao.updateProject(project);
		
		appointTask.setStatus(false);
		taskDao.updateTask(appointTask);
		
		//记录完成Task的employee
		Log appointLog = new Log(employee, appointTask);
		logDao.addLog(appointLog);
		
		//为项目负责人创建新建客户资料登记单的Task,同时更新项目状态
		project.setStep(ProjectStepEnum.STEP3);
		project.setStatus(ProjectStatusEnum.CREATE_KHZLDJD);
		projectDao.updateProject(project);
		
		taskDao.addTask(new Task(project, ProjectStepEnum.STEP3, 
				ProjectStatusEnum.CREATE_KHZLDJD, 
				appointEmployee));
		
		logger.info("指定项目负责人信息:" + project.getName() + "(" + appointEmployee.getName() + ")");
		
		return ret;
	}
	
}
