package cn.edu.tongji.anliantest.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.tongji.anliantest.dao.EmployeeDao;
import cn.edu.tongji.anliantest.dao.LogDao;
import cn.edu.tongji.anliantest.dao.PJFADao;
import cn.edu.tongji.anliantest.dao.ProjectDao;
import cn.edu.tongji.anliantest.dao.TaskDao;
import cn.edu.tongji.anliantest.model.Log;
import cn.edu.tongji.anliantest.model.PJFA;
import cn.edu.tongji.anliantest.model.Project;
import cn.edu.tongji.anliantest.model.ProjectStatusEnum;
import cn.edu.tongji.anliantest.model.ProjectStepEnum;
import cn.edu.tongji.anliantest.model.Task;
import cn.edu.tongji.anliantest.service.PJFAService;
import cn.edu.tongji.anliantest.util.DataWrapper;

@Service("pjfaService")
public class PJFAServiceImpl implements PJFAService{

	private static final Logger logger = LoggerFactory.getLogger(PJFAServiceImpl.class);
	
	@Autowired
    private PJFADao pjfaDao;
	
	@Autowired
    private TaskDao taskDao;
	@Autowired
	private LogDao logDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private ProjectDao projectDao;
	
	@Override
	public DataWrapper<PJFA> getPJFAById(Long pjfaId) {
		return null;
	}

	@Override
	public DataWrapper<PJFA> getPJFAByProjectId(Long projectId) {
		DataWrapper<PJFA> ret = new DataWrapper<PJFA>();
		
		ret.setData(pjfaDao.getPJFAByProjectId(projectId));
		
		return ret;
	}

	@Override
	public DataWrapper<PJFA> addPJFA(PJFA pjfa, Long taskId, Long employeeId) {
		DataWrapper<PJFA> ret = new DataWrapper<PJFA>();
		
		Task createTask = taskDao.getTaskById(taskId);
		Project project = createTask.getProject();
		
		pjfa.setProject(project);
		
		pjfaDao.addPJFA(pjfa);
		
		//完成上传评价方案的Task
		createTask.setStatus(false);
		taskDao.updateTask(createTask);
		
		//记录完成Task的employee
		Log createLog = new Log(employeeDao.getEmployeeById(employeeId), createTask);
		logDao.addLog(createLog);
		
		//为项目负责人创建新建评价方案审核记录的Task,同时更新项目状态
		project.setStep(ProjectStepEnum.STEP3);
		project.setStatus(ProjectStatusEnum.CREATE_PJFASHJL);
		projectDao.updateProject(project);
		
		taskDao.addTask(new Task(project, ProjectStepEnum.STEP3, 
				ProjectStatusEnum.CREATE_PJFASHJL, 
				project.getProjectEmployee()));
		
		ret.setData(pjfaDao.getPJFAById(pjfa.getId()));
		
		logger.info("添加评价方案信息:"+pjfa.getId());
		
		return ret;
	}

	@Override
	public DataWrapper<PJFA> updatePJFA(PJFA pjfa) {
		return null;
	}

	@Override
	public DataWrapper<Void> deletePJFA(Long pjfaId) {
		return null;
	}
	
}
