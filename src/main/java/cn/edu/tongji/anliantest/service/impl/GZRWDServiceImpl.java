package cn.edu.tongji.anliantest.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.tongji.anliantest.dao.DepartmentDao;
import cn.edu.tongji.anliantest.dao.EmployeeDao;
import cn.edu.tongji.anliantest.dao.GZRWDTableDao;
import cn.edu.tongji.anliantest.dao.LogDao;
import cn.edu.tongji.anliantest.dao.ProjectDao;
import cn.edu.tongji.anliantest.dao.TaskDao;
import cn.edu.tongji.anliantest.model.DepartmentTypeEnum;
import cn.edu.tongji.anliantest.model.GZRWDTable;
import cn.edu.tongji.anliantest.model.Log;
import cn.edu.tongji.anliantest.model.Project;
import cn.edu.tongji.anliantest.model.ProjectStatusEnum;
import cn.edu.tongji.anliantest.model.ProjectStepEnum;
import cn.edu.tongji.anliantest.model.Task;
import cn.edu.tongji.anliantest.service.GZRWDService;
import cn.edu.tongji.anliantest.util.DataWrapper;

@Service("gzrwdService")
public class GZRWDServiceImpl implements GZRWDService{

	private static final Logger logger = LoggerFactory.getLogger(HTPSJLServiceImpl.class);
	
	@Autowired
    private GZRWDTableDao gzrwdTableDao;
	
	@Autowired
    private TaskDao taskDao;
	@Autowired
	private LogDao logDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private ProjectDao projectDao;
	
	@Override
	public DataWrapper<GZRWDTable> getGZRWDTableById(Long gzrwdTableId) {
		DataWrapper<GZRWDTable> ret = new DataWrapper<>();
        
		GZRWDTable gzrwdTable = gzrwdTableDao.getGZRWDById(gzrwdTableId);
        ret.setData(gzrwdTable);
        
        return ret;
	}

	@Override
	public DataWrapper<GZRWDTable> addGZRWDTable(GZRWDTable gzrwdTable, Long taskId, Long employeeId) {
		DataWrapper<GZRWDTable> ret = new DataWrapper<>();
		
		Task createTask = taskDao.getTaskById(taskId);
		Project project = createTask.getProject();
		
		gzrwdTable.setProject(project);
		
		gzrwdTableDao.addGZRWD(gzrwdTable);
		
		//完成新建工作任务单的Task
		createTask.setStatus(false);
		taskDao.updateTask(createTask);
		
		//记录完成Task的employee
		Log createLog = new Log(employeeDao.getEmployeeById(employeeId), createTask);
		logDao.addLog(createLog);
		
		//为行政部创建指定项目负责人的Task,同时更新项目状态
		project.setStep(ProjectStepEnum.STEP2);
		project.setStatus(ProjectStatusEnum.APPOINT_XMFZR);
		projectDao.updateProject(project);
		
		taskDao.addTask(new Task(project, ProjectStepEnum.STEP2, 
				ProjectStatusEnum.APPOINT_XMFZR, 
				departmentDao.getDepartmentByType(DepartmentTypeEnum.ADMIN)));
		
		ret.setData(gzrwdTableDao.getGZRWDById(gzrwdTable.getId()));
		
		logger.info("添加工作任务单信息:"+gzrwdTable.getId()+"("+gzrwdTable.getIssueDate()+")");
		
		return ret;
	}

	@Override
	public DataWrapper<GZRWDTable> updateGZRWDTable(GZRWDTable gzrwdTable) {
		DataWrapper<GZRWDTable> ret = new DataWrapper<>();
		
		gzrwdTableDao.updateGZRWD(gzrwdTable);
		
		ret.setData(gzrwdTableDao.getGZRWDById(gzrwdTable.getId()));
		
		logger.info("更新工作任务单信息:"+gzrwdTable.getId()+"("+gzrwdTable.getIssueDate()+")");
		
		return ret;
	}

	@Override
	public DataWrapper<Void> deleteGZRWDTable(Long gzrwdTableId) {
		DataWrapper<Void> ret = new DataWrapper<>();
		
		gzrwdTableDao.deleteGZRWD(gzrwdTableId);
		
		logger.info("删除工作任务单信息:" + "(" + gzrwdTableId + ")");
		
		return ret;
	}

	@Override
	public DataWrapper<GZRWDTable> getGZRWDTableByProject(Long projectId) {
		DataWrapper<GZRWDTable> ret = new DataWrapper<GZRWDTable>();
        
		GZRWDTable gzrwdTable = gzrwdTableDao.getGZRWDByProjectId(projectId);
        ret.setData(gzrwdTable);
        
        return ret;
	}
	
}
