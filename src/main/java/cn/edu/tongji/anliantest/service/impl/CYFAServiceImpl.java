package cn.edu.tongji.anliantest.service.impl;

import java.io.File;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.tongji.anliantest.dao.CYFATableDao;
import cn.edu.tongji.anliantest.dao.DepartmentDao;
import cn.edu.tongji.anliantest.dao.EmployeeDao;
import cn.edu.tongji.anliantest.dao.LogDao;
import cn.edu.tongji.anliantest.dao.ProjectDao;
import cn.edu.tongji.anliantest.dao.TaskDao;
import cn.edu.tongji.anliantest.document.CYFADocument;
import cn.edu.tongji.anliantest.model.Log;
import cn.edu.tongji.anliantest.model.Project;
import cn.edu.tongji.anliantest.model.ProjectStatusEnum;
import cn.edu.tongji.anliantest.model.ProjectStepEnum;
import cn.edu.tongji.anliantest.model.Task;
import cn.edu.tongji.anliantest.model.experiment.CYFATable;
import cn.edu.tongji.anliantest.service.CYFAService;
import cn.edu.tongji.anliantest.util.ApplicationContextUtil;
import cn.edu.tongji.anliantest.util.DataWrapper;

@Service("cyfaService")
public class CYFAServiceImpl implements CYFAService{
	private static final Logger logger = LoggerFactory.getLogger(CYFAServiceImpl.class);

	@Autowired
	private CYFATableDao cyfaTableDao;
	
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
	public DataWrapper<CYFATable> getCYFATableById(Long cyfaTableId) {
		DataWrapper<CYFATable> ret = new DataWrapper<CYFATable>();
		
		ret.setData(cyfaTableDao.getCYFATableById(cyfaTableId));
		
		return ret;
	}

	@Override
	public DataWrapper<CYFATable> getCYFATableProjectId(Long projectId) {
		DataWrapper<CYFATable> ret = new DataWrapper<CYFATable>();
		
		ret.setData(cyfaTableDao.getCYFATableByProjectId(projectId));
		
		return ret;
	}

	@Override
	public DataWrapper<CYFATable> addCYFATable(CYFATable cyfaTable,
			Long taskId, Long employeeId) {
		return null;
	}

	@Override
	public DataWrapper<CYFATable> updateCYFATable(CYFATable cyfaTable) {
		return null;
	}

	@Override
	public DataWrapper<Void> deleteCYFATable(Long cyfaTableId) {
		return null;
	}

	@Override
	public DataWrapper<CYFATable> confirmCYFATable(CYFATable cyfaTable,
			Long taskId, Long employeeId) {
		DataWrapper<CYFATable> ret = new DataWrapper<CYFATable>();
		
		Task confirmTask = taskDao.getTaskById(taskId);
		Project project = confirmTask.getProject();
		
		for(int i = 0; i < cyfaTable.getItems().size(); i++) {
			for(int j = 0; j < cyfaTable.getItems().get(i).getItems().size(); j++) {
				for(int k = 0; k < cyfaTable.getItems().get(i).getItems().get(j).getItems().size(); k++) {
					if(cyfaTable.getItems().get(i).getItems().get(j).getItems().get(k).getSampleCount() <= 0) {
						cyfaTable.getItems().get(i).getItems().get(j).getItems().remove(k);
					}
				}
			}
		}
		cyfaTable.setConfirm(true);
		
		cyfaTableDao.updateCYFATable(cyfaTable);
		
		//完成确认采样方案的Task
		confirmTask.setStatus(false);
		taskDao.updateTask(confirmTask);
		
		//记录完成Task的employee
		Log confirmLog = new Log(employeeDao.getEmployeeById(employeeId), confirmTask);
		logDao.addLog(confirmLog);
		
		//为项目负责人创建新建送样、收样记录表的Task,同时更新项目状态
		project.setStep(ProjectStepEnum.STEP5);
		project.setStatus(ProjectStatusEnum.CREATE_SYSYJL);
		projectDao.updateProject(project);
		
		taskDao.addTask(new Task(project, ProjectStepEnum.STEP5, 
				ProjectStatusEnum.CREATE_SYSYJL, 
				project.getProjectEmployee()));
		
		ret.setData(cyfaTableDao.getCYFATableById(cyfaTable.getId()));
		
		logger.info("确认采样方案信息:"+cyfaTable.getId());
		
		return ret;
	}

	@Override
	public File getCYFAFile(Long projectId) {
		CYFATable cyfaTable = cyfaTableDao.getCYFATableByProjectId(projectId);
		
		ServletContext context = ApplicationContextUtil.getContext().getServletContext();
		Project project = cyfaTable.getProject();
		String filePath = context.getRealPath("report") + File.separator + project.getNumber() + File.separator + project.getNumber() + "-" + project.getName() + "-" + "采样方案（有毒物质、粉尘）" + ".doc";
		File file = new File(filePath);
		if(file.exists()){
			logger.info("下载" + project.getName() + "采样方案（有毒物质、粉尘）");
			return file;
		}
		else {
			CYFADocument.generate(cyfaTable.getId());
			file = new File(filePath);
			if(file.exists()) {
				logger.info("下载" + project.getName() + "采样方案（有毒物质、粉尘）");
				return file;
			}else{
				return null;
			}
		}
	}

}
