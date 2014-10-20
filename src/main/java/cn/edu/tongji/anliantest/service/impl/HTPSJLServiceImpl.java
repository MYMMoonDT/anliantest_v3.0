package cn.edu.tongji.anliantest.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.tongji.anliantest.dao.DepartmentDao;
import cn.edu.tongji.anliantest.dao.EmployeeDao;
import cn.edu.tongji.anliantest.dao.HTPSJLDao;
import cn.edu.tongji.anliantest.dao.LogDao;
import cn.edu.tongji.anliantest.dao.ProjectDao;
import cn.edu.tongji.anliantest.dao.TaskDao;
import cn.edu.tongji.anliantest.model.DepartmentTypeEnum;
import cn.edu.tongji.anliantest.model.HTPSJLTable;
import cn.edu.tongji.anliantest.model.Log;
import cn.edu.tongji.anliantest.model.Project;
import cn.edu.tongji.anliantest.model.ProjectStatusEnum;
import cn.edu.tongji.anliantest.model.ProjectStepEnum;
import cn.edu.tongji.anliantest.model.Task;
import cn.edu.tongji.anliantest.service.HTPSJLService;
import cn.edu.tongji.anliantest.util.DataWrapper;
import cn.edu.tongji.anliantest.util.TableNumEnum;

@Service("htpsjlService")
public class HTPSJLServiceImpl implements HTPSJLService{

	private static final Logger logger = LoggerFactory.getLogger(HTPSJLServiceImpl.class);
	
	@Autowired
    private HTPSJLDao htpsjlDao;
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
        
		HTPSJLTable htpsjlTable = htpsjlDao.getHTPSJLById(htpsjlTableId);
        ret.setData(htpsjlTable);
        
        return ret;
	}

	@Override
	public DataWrapper<HTPSJLTable> addHTPSJLTable(HTPSJLTable htpsjlTable, Long employeeId) {
		DataWrapper<HTPSJLTable> ret = new DataWrapper<>();
		
		htpsjlTable.setTableNum(TableNumEnum.HTPSJL);
		
		htpsjlDao.addHTPSJL(htpsjlTable);
		
		//完成新建合同评审记录的Task
		Project project = projectDao.getProjectById(htpsjlTable.getProject().getId());
		Task createTask = taskDao.getTaskByProjectInfo(project, ProjectStepEnum.STEP1, ProjectStatusEnum.CREATE_HTPSJL);
		if(createTask != null) {
			createTask.setStatus(false);
			taskDao.updateTask(createTask);
		}
		
		//记录完成Task的employee
		Log createLog = new Log(employeeDao.getEmployeeById(employeeId), createTask);
		logDao.addLog(createLog);
		
		//为评价部、检测部、行政部、质控部、总经理、质量负责人创建合同评审记录签字的Task
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
		
		ret.setData(htpsjlDao.getHTPSJLById(htpsjlTable.getId()));
		
		logger.info("添加合同评审记录信息:"+htpsjlTable.getId()+"("+htpsjlTable.getCreateDate()+")");
		
		return ret;
	}

	@Override
	public DataWrapper<HTPSJLTable> updateHTPSJLTable(HTPSJLTable htpsjlTable) {
		DataWrapper<HTPSJLTable> ret = new DataWrapper<>();
		
		htpsjlDao.updateHTPSJL(htpsjlTable);
		
		ret.setData(htpsjlDao.getHTPSJLById(htpsjlTable.getId()));
		
		logger.info("更新合同评审记录信息:"+htpsjlTable.getId()+"("+htpsjlTable.getCreateDate()+")");
		
		return ret;
	}

	@Override
	public DataWrapper<Void> deleteHTPSJLTable(Long htpsjlTableId) {
		DataWrapper<Void> ret = new DataWrapper<>();
		
		htpsjlDao.deleteHTPSJL(htpsjlTableId);
		
		logger.info("删除合同评审记录信息:" + htpsjlTableId);
		
		return ret;
	}

}
