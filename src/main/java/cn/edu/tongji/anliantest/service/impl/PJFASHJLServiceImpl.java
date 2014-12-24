package cn.edu.tongji.anliantest.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.tongji.anliantest.dao.DepartmentDao;
import cn.edu.tongji.anliantest.dao.EmployeeDao;
import cn.edu.tongji.anliantest.dao.LogDao;
import cn.edu.tongji.anliantest.dao.PJFASHJLItemDao;
import cn.edu.tongji.anliantest.dao.PJFASHJLTableDao;
import cn.edu.tongji.anliantest.dao.ProjectDao;
import cn.edu.tongji.anliantest.dao.TaskDao;
import cn.edu.tongji.anliantest.model.DepartmentTypeEnum;
import cn.edu.tongji.anliantest.model.Employee;
import cn.edu.tongji.anliantest.model.Log;
import cn.edu.tongji.anliantest.model.PJFASHJLItem;
import cn.edu.tongji.anliantest.model.PJFASHJLTable;
import cn.edu.tongji.anliantest.model.Project;
import cn.edu.tongji.anliantest.model.ProjectStatusEnum;
import cn.edu.tongji.anliantest.model.ProjectStepEnum;
import cn.edu.tongji.anliantest.model.Task;
import cn.edu.tongji.anliantest.service.PJFASHJLService;
import cn.edu.tongji.anliantest.util.DataWrapper;

@Service("pjfashjlService")
public class PJFASHJLServiceImpl implements PJFASHJLService{
	
	private static final Logger logger = LoggerFactory.getLogger(PJFASHJLServiceImpl.class);

	@Autowired
    private PJFASHJLTableDao pjfashjlTableDao;
	@Autowired
    private PJFASHJLItemDao pjfashjlItemDao;
	
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
	public DataWrapper<PJFASHJLTable> getPJFASHJLById(Long pjfashjlTableId) {
		DataWrapper<PJFASHJLTable> ret = new DataWrapper<PJFASHJLTable>();
		
		ret.setData(pjfashjlTableDao.getPJFASHJLById(pjfashjlTableId));
		
		return ret;
	}

	@Override
	public DataWrapper<PJFASHJLTable> getPJFASHJLByProjectId(Long projectId) {
		DataWrapper<PJFASHJLTable> ret = new DataWrapper<PJFASHJLTable>();
		
		ret.setData(pjfashjlTableDao.getPJFASHJLByProjectId(projectId));
		
		return ret;
	}

	@Override
	public DataWrapper<PJFASHJLTable> addPJFASHJL(PJFASHJLTable pjfashjlTable,
			Long taskId, Long employeeId) {
		
		DataWrapper<PJFASHJLTable> ret = new DataWrapper<PJFASHJLTable>();
		
		Task createTask = taskDao.getTaskById(taskId);
		Project project = createTask.getProject();
		
		pjfashjlTable.setProject(project);
		
		pjfashjlTableDao.addPJFASHJL(pjfashjlTable);
		
		//完成新建评价方案审核记录的Task
		createTask.setStatus(false);
		taskDao.updateTask(createTask);
		
		//记录完成Task的employee
		Log createLog = new Log(employeeDao.getEmployeeById(employeeId), createTask);
		logDao.addLog(createLog);
		
		//为技术负责人、总经理创建评价方案审核记录签字的Task,同时更新项目状态
		project.setStep(ProjectStepEnum.STEP3);
		project.setStatus(ProjectStatusEnum.SIGN_PJFASHJL);
		projectDao.updateProject(project);
		
		taskDao.addTask(new Task(project, ProjectStepEnum.STEP3, 
				ProjectStatusEnum.SIGN_PJFASHJL, 
				departmentDao.getDepartmentByType(DepartmentTypeEnum.MANAGER)));
		taskDao.addTask(new Task(project, ProjectStepEnum.STEP3, 
				ProjectStatusEnum.SIGN_PJFASHJL, 
				departmentDao.getDepartmentByType(DepartmentTypeEnum.TEACHNICAL_DIRECTOR)));
		
		ret.setData(pjfashjlTableDao.getPJFASHJLById(pjfashjlTable.getId()));
		
		logger.info("添加评价方案审核记录信息:"+pjfashjlTable.getId());
		
		return ret;
	}

	@Override
	public DataWrapper<PJFASHJLTable> updatePJFASHJL(PJFASHJLTable pjfashjlTable) {
		return null;
	}

	@Override
	public DataWrapper<PJFASHJLTable> deletePJFASHJL(Long pjfashjlTableId) {
		return null;
	}

	@Override
	public DataWrapper<Void> signPJFASHJL(PJFASHJLItem pjfashjlItem,
			Long taskId, Long employeeId) {
		DataWrapper<Void> ret = new DataWrapper<Void>();
		
		//完成评价方案审核记录签字的Task
		Task signTask = taskDao.getTaskById(taskId);
		Employee employee = employeeDao.getEmployeeById(employeeId);
		
		Project project = signTask.getProject();
		PJFASHJLTable pjfashjlTable = pjfashjlTableDao.getPJFASHJLByProjectId(project.getId());
		pjfashjlTable.getItems().add(pjfashjlItem);
		pjfashjlTableDao.updatePJFASHJL(pjfashjlTable);
		
		signTask.setStatus(false);
		taskDao.updateTask(signTask);
		
		//记录完成Task的employee
		Log signLog = new Log(employee, signTask);
		logDao.addLog(signLog);
		
		//为项目负责人创建新建检测通知单的Task,同时更新项目状态
		List<Task> taskList = taskDao.getTaskByProjectInfo(project, ProjectStepEnum.STEP3, ProjectStatusEnum.SIGN_PJFASHJL);
		
		if(taskList != null && taskList.size() > 0) {
			
		} else {
			project.setStep(ProjectStepEnum.STEP3);
			project.setStatus(ProjectStatusEnum.CREATE_JCTZD);
			projectDao.updateProject(project);
			
			taskDao.addTask(new Task(project, ProjectStepEnum.STEP3, 
					ProjectStatusEnum.CREATE_JCTZD, 
					project.getProjectEmployee()));
		}
		
		logger.info("评价方案审核记录签字信息:" + pjfashjlTable.getId());
		
		return ret;
	}

}
