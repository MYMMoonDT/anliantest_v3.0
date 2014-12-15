package cn.edu.tongji.anliantest.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.tongji.anliantest.dao.DepartmentDao;
import cn.edu.tongji.anliantest.dao.EmployeeDao;
import cn.edu.tongji.anliantest.dao.HTPSJLItemDao;
import cn.edu.tongji.anliantest.dao.HTPSJLTableDao;
import cn.edu.tongji.anliantest.dao.LogDao;
import cn.edu.tongji.anliantest.dao.ProjectDao;
import cn.edu.tongji.anliantest.dao.TaskDao;
import cn.edu.tongji.anliantest.model.DepartmentTypeEnum;
import cn.edu.tongji.anliantest.model.Employee;
import cn.edu.tongji.anliantest.model.HTPSJLItem;
import cn.edu.tongji.anliantest.model.HTPSJLTable;
import cn.edu.tongji.anliantest.model.Log;
import cn.edu.tongji.anliantest.model.Project;
import cn.edu.tongji.anliantest.model.ProjectStatusEnum;
import cn.edu.tongji.anliantest.model.ProjectStepEnum;
import cn.edu.tongji.anliantest.model.Task;
import cn.edu.tongji.anliantest.service.HTPSJLService;
import cn.edu.tongji.anliantest.util.DataWrapper;

@Service("htpsjlService")
public class HTPSJLServiceImpl implements HTPSJLService{

	private static final Logger logger = LoggerFactory.getLogger(HTPSJLServiceImpl.class);
	
	@Autowired
    private HTPSJLTableDao htpsjlTableDao;
	@Autowired
    private HTPSJLItemDao htpsjlItemDao;
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private TaskDao taskDao;
	@Autowired
	private LogDao logDao;
	
	@Override
	public DataWrapper<HTPSJLTable> getHTPSJLTableById(Long htpsjlTableId) {
		DataWrapper<HTPSJLTable> ret = new DataWrapper<>();
        
		HTPSJLTable htpsjlTable = htpsjlTableDao.getHTPSJLById(htpsjlTableId);
        ret.setData(htpsjlTable);
        
        return ret;
	}

	@Override
	public DataWrapper<HTPSJLTable> addHTPSJLTable(HTPSJLTable htpsjlTable, Long employeeId) {
		DataWrapper<HTPSJLTable> ret = new DataWrapper<>();
		
		htpsjlTableDao.addHTPSJL(htpsjlTable);
		
		//完成新建合同评审记录的Task
		Project project = projectDao.getProjectById(htpsjlTable.getProject().getId());
		List<Task> taskList = taskDao.getTaskByProjectInfo(project, ProjectStepEnum.STEP1, ProjectStatusEnum.CREATE_HTPSJL);
		Task createTask = null;
		if(taskList != null && taskList.size() > 0) {
			createTask = taskList.get(0);
		}
		
		if(createTask != null) {
			createTask.setStatus(false);
			taskDao.updateTask(createTask);
		}
		
		//记录完成Task的employee
		Log createLog = new Log(employeeDao.getEmployeeById(employeeId), createTask);
		logDao.addLog(createLog);
		
		//为评价部、检测部、行政部、质控部、总经理、技术负责人创建合同评审记录签字的Task,同时更新项目状态
		project.setStep(ProjectStepEnum.STEP1);
		project.setStatus(ProjectStatusEnum.SIGN_HTPSJL);
		projectDao.updateProject(project);
		
		taskDao.addTask(new Task(project, ProjectStepEnum.STEP1, 
				ProjectStatusEnum.SIGN_HTPSJL, 
				departmentDao.getDepartmentByType(DepartmentTypeEnum.EVALUATION)));
		taskDao.addTask(new Task(project, ProjectStepEnum.STEP1, 
				ProjectStatusEnum.SIGN_HTPSJL, 
				departmentDao.getDepartmentByType(DepartmentTypeEnum.DETECTION)));
		taskDao.addTask(new Task(project, ProjectStepEnum.STEP1, 
				ProjectStatusEnum.SIGN_HTPSJL, 
				departmentDao.getDepartmentByType(DepartmentTypeEnum.ADMIN)));
		taskDao.addTask(new Task(project, ProjectStepEnum.STEP1, 
				ProjectStatusEnum.SIGN_HTPSJL, 
				departmentDao.getDepartmentByType(DepartmentTypeEnum.QUALITY)));
		taskDao.addTask(new Task(project, ProjectStepEnum.STEP1, 
				ProjectStatusEnum.SIGN_HTPSJL, 
				departmentDao.getDepartmentByType(DepartmentTypeEnum.MANAGER)));
		taskDao.addTask(new Task(project, ProjectStepEnum.STEP1, 
				ProjectStatusEnum.SIGN_HTPSJL, 
				departmentDao.getDepartmentByType(DepartmentTypeEnum.TEACHNICAL_DIRECTOR)));
		
		ret.setData(htpsjlTableDao.getHTPSJLById(htpsjlTable.getId()));
		
		logger.info("添加合同评审记录信息:"+htpsjlTable.getId()+"("+htpsjlTable.getCreateDate()+")");
		
		return ret;
	}

	@Override
	public DataWrapper<HTPSJLTable> updateHTPSJLTable(HTPSJLTable htpsjlTable) {
		DataWrapper<HTPSJLTable> ret = new DataWrapper<>();
		
		htpsjlTableDao.updateHTPSJL(htpsjlTable);
		
		ret.setData(htpsjlTableDao.getHTPSJLById(htpsjlTable.getId()));
		
		logger.info("更新合同评审记录信息:"+htpsjlTable.getId()+"("+htpsjlTable.getCreateDate()+")");
		
		return ret;
	}

	@Override
	public DataWrapper<Void> deleteHTPSJLTable(Long htpsjlTableId) {
		DataWrapper<Void> ret = new DataWrapper<>();
		
		htpsjlTableDao.deleteHTPSJL(htpsjlTableId);
		
		logger.info("删除合同评审记录信息:" + htpsjlTableId);
		
		return ret;
	}

	@Override
	public DataWrapper<Void> signHTPSJLTable(Long taskId, Long employeeId) {
		DataWrapper<Void> ret = new DataWrapper<>();
		
		//完成合同评审记录签字的Task
		Task signTask = taskDao.getTaskById(taskId);
		Employee employee = employeeDao.getEmployeeById(employeeId);
		
		Project project = signTask.getProject();
		HTPSJLTable htpsjlTable = htpsjlTableDao.getHTPSJLByProjectId(project.getId());
		for(HTPSJLItem item : htpsjlTable.getItems()) {
			if(item.getDepartment().getId().equals(employee.getDepartment().getId())) {
				item.setEmployee(employee);
				htpsjlItemDao.updateHTPSJL(item);
				break;
			}
		}
		signTask.setStatus(false);
		taskDao.updateTask(signTask);
		
		//记录完成Task的employee
		Log signLog = new Log(employee, signTask);
		logDao.addLog(signLog);
		
		//为技术负责人创建工作任务单的Task,同时更新项目状态
		List<Task> taskList = taskDao.getTaskByProjectInfo(project, ProjectStepEnum.STEP1, ProjectStatusEnum.SIGN_HTPSJL);
		
		if(taskList != null && taskList.size() > 0) {
			
		} else {
			project.setStep(ProjectStepEnum.STEP2);
			project.setStatus(ProjectStatusEnum.CREATE_GZRWD);
			projectDao.updateProject(project);
			
			taskDao.addTask(new Task(project, ProjectStepEnum.STEP2, 
					ProjectStatusEnum.CREATE_GZRWD, 
					departmentDao.getDepartmentByType(DepartmentTypeEnum.TEACHNICAL_DIRECTOR)));
		}
		
		logger.info("合同评审记录签字信息:" + htpsjlTable.getId());
		
		return ret;
	}

	@Override
	public DataWrapper<HTPSJLTable> getHTPSJLTableByProject(Long projectId) {
		DataWrapper<HTPSJLTable> ret = new DataWrapper<>();
        
		HTPSJLTable htpsjlTable = htpsjlTableDao.getHTPSJLByProjectId(projectId);
        ret.setData(htpsjlTable);
        
        return ret;
	}

}
