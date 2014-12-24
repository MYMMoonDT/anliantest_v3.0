package cn.edu.tongji.anliantest.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.tongji.anliantest.dao.EmployeeDao;
import cn.edu.tongji.anliantest.dao.LogDao;
import cn.edu.tongji.anliantest.dao.ProjectDao;
import cn.edu.tongji.anliantest.dao.TaskDao;
import cn.edu.tongji.anliantest.dao.XCDCJLTableDao;
import cn.edu.tongji.anliantest.model.Log;
import cn.edu.tongji.anliantest.model.Project;
import cn.edu.tongji.anliantest.model.ProjectStatusEnum;
import cn.edu.tongji.anliantest.model.ProjectStepEnum;
import cn.edu.tongji.anliantest.model.Task;
import cn.edu.tongji.anliantest.model.XCDCJLTable;
import cn.edu.tongji.anliantest.service.XCDCJLService;
import cn.edu.tongji.anliantest.util.DataWrapper;

@Service("xcdcjlService")
public class XCDCJLServiceImpl implements XCDCJLService{

	private static final Logger logger = LoggerFactory.getLogger(XCDCJLServiceImpl.class);
	
	@Autowired
	private XCDCJLTableDao xcdcjlTableDao;
	
	@Autowired
    private TaskDao taskDao;
	@Autowired
	private LogDao logDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private ProjectDao projectDao;
	
	@Override
	public DataWrapper<XCDCJLTable> getXCDCJLById(Long xcdcjlTableId) {
		return null;
	}
	@Override
	public DataWrapper<XCDCJLTable> addXCDCJL(XCDCJLTable xcdcjlTable,
			Long taskId, Long employeeId) {	
		DataWrapper<XCDCJLTable> ret = new DataWrapper<>();
		
		Task createTask = taskDao.getTaskById(taskId);
		Project project = createTask.getProject();
		
		xcdcjlTable.setProject(project);
		
		xcdcjlTableDao.addXCDCJL(xcdcjlTable);
		
		//完成新建现场调查记录的Task
		createTask.setStatus(false);
		taskDao.updateTask(createTask);
		
		//记录完成Task的employee
		Log createLog = new Log(employeeDao.getEmployeeById(employeeId), createTask);
		logDao.addLog(createLog);
		
		//为项目负责人创建上传评价方案的Task,同时更新项目状态
		project.setStep(ProjectStepEnum.STEP3);
		project.setStatus(ProjectStatusEnum.UPLOAD_PJFA);
		projectDao.updateProject(project);
		
		taskDao.addTask(new Task(project, ProjectStepEnum.STEP3, 
				ProjectStatusEnum.UPLOAD_PJFA, 
				project.getProjectEmployee()));
		
		ret.setData(xcdcjlTableDao.getXCDCJLById(xcdcjlTable.getId()));
		
		logger.info("添加现场调查记录信息:"+xcdcjlTable.getId());
		
		return ret;
	}
	@Override
	public DataWrapper<XCDCJLTable> updateXCDCJL(XCDCJLTable xcdcjlTable) {
		return null;
	}
	@Override
	public DataWrapper<Void> deleteXCDCJL(Long xcdcjlTableId) {
		return null;
	}
	@Override
	public DataWrapper<XCDCJLTable> getXCDCJLByProject(Long projectId) {
		
		DataWrapper<XCDCJLTable> ret = new DataWrapper<XCDCJLTable>();
        
		XCDCJLTable xcdcjlTable = xcdcjlTableDao.getXCDCJLByProjectId(projectId);
        ret.setData(xcdcjlTable);
        
        return ret;
	}
}
