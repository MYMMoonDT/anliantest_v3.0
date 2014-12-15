package cn.edu.tongji.anliantest.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.tongji.anliantest.dao.EmployeeDao;
import cn.edu.tongji.anliantest.dao.KHZLDJDTableDao;
import cn.edu.tongji.anliantest.dao.LogDao;
import cn.edu.tongji.anliantest.dao.ProjectDao;
import cn.edu.tongji.anliantest.dao.TaskDao;
import cn.edu.tongji.anliantest.model.KHZLDJDTable;
import cn.edu.tongji.anliantest.model.Log;
import cn.edu.tongji.anliantest.model.Project;
import cn.edu.tongji.anliantest.model.ProjectStatusEnum;
import cn.edu.tongji.anliantest.model.ProjectStepEnum;
import cn.edu.tongji.anliantest.model.Task;
import cn.edu.tongji.anliantest.service.KHZLDJDService;
import cn.edu.tongji.anliantest.util.DataWrapper;

@Service("khzldjService")
public class KHZLDJDServiceImpl implements KHZLDJDService{
	
	private static final Logger logger = LoggerFactory.getLogger(KHZLDJDServiceImpl.class);
	
	@Autowired
    private KHZLDJDTableDao khzldjdTableDao;
	
	@Autowired
    private TaskDao taskDao;
	@Autowired
	private LogDao logDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private ProjectDao projectDao;
	
	@Override
	public DataWrapper<KHZLDJDTable> getKHZLDJDById(Long khzldjdTableId) {
		DataWrapper<KHZLDJDTable> ret = new DataWrapper<>();
        
		KHZLDJDTable khzldjdTable = khzldjdTableDao.getKHZLDJDById(khzldjdTableId);
        ret.setData(khzldjdTable);
        
        return ret;
	}

	@Override
	public DataWrapper<KHZLDJDTable> addKHZLDJD(KHZLDJDTable khzldjdTable, Long taskId, Long employeeId) {
		DataWrapper<KHZLDJDTable> ret = new DataWrapper<>();
		
		Task createTask = taskDao.getTaskById(taskId);
		Project project = createTask.getProject();
		
		khzldjdTable.setProject(project);
		
		khzldjdTableDao.addKHZLDJD(khzldjdTable);
		
		//完成新建客户资料登记单的Task
		createTask.setStatus(false);
		taskDao.updateTask(createTask);
		
		//记录完成Task的employee
		Log createLog = new Log(employeeDao.getEmployeeById(employeeId), createTask);
		logDao.addLog(createLog);
		
		//为项目负责人创建新建现场调查记录的Task,同时更新项目状态
		project.setStep(ProjectStepEnum.STEP3);
		project.setStatus(ProjectStatusEnum.CREATE_XCDCJL);
		projectDao.updateProject(project);
		
		taskDao.addTask(new Task(project, ProjectStepEnum.STEP3, 
				ProjectStatusEnum.CREATE_XCDCJL, 
				project.getProjectEmployee()));
		
		ret.setData(khzldjdTableDao.getKHZLDJDById(khzldjdTable.getId()));
		
		logger.info("添加客户资料登记单信息:"+khzldjdTable.getId());
		
		return ret;
	}

	@Override
	public DataWrapper<KHZLDJDTable> updateKHZLDJD(KHZLDJDTable khzldjdTable) {
		DataWrapper<KHZLDJDTable> ret = new DataWrapper<>();
		
		khzldjdTableDao.updateKHZLDJD(khzldjdTable);
		
		ret.setData(khzldjdTableDao.getKHZLDJDById(khzldjdTable.getId()));
		
		logger.info("更新客户资料登记单信息:"+khzldjdTable.getId());
		
		return ret;
	}

	@Override
	public DataWrapper<Void> deleteKHZLDJD(Long khzldjdTableId) {
		DataWrapper<Void> ret = new DataWrapper<>();
		
		khzldjdTableDao.deleteKHZLDJD(khzldjdTableId);
		
		logger.info("删除客户资料登记单信息:" + khzldjdTableId);
		
		return ret;
	}
	
}
