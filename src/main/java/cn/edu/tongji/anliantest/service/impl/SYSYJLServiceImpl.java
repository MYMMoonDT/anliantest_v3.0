package cn.edu.tongji.anliantest.service.impl;

import java.io.File;
import java.util.Collections;
import java.util.Date;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.tongji.anliantest.dao.DepartmentDao;
import cn.edu.tongji.anliantest.dao.EmployeeDao;
import cn.edu.tongji.anliantest.dao.JCBGTableDao;
import cn.edu.tongji.anliantest.dao.JCTZDTableDao;
import cn.edu.tongji.anliantest.dao.JGPJTableDao;
import cn.edu.tongji.anliantest.dao.JSJGTableDao;
import cn.edu.tongji.anliantest.dao.LogDao;
import cn.edu.tongji.anliantest.dao.ProjectDao;
import cn.edu.tongji.anliantest.dao.SYSYJLTableDao;
import cn.edu.tongji.anliantest.dao.TaskDao;
import cn.edu.tongji.anliantest.document.SYSYJLDocument;
import cn.edu.tongji.anliantest.model.DepartmentTypeEnum;
import cn.edu.tongji.anliantest.model.JCTZDTable;
import cn.edu.tongji.anliantest.model.Log;
import cn.edu.tongji.anliantest.model.Project;
import cn.edu.tongji.anliantest.model.ProjectStatusEnum;
import cn.edu.tongji.anliantest.model.ProjectStepEnum;
import cn.edu.tongji.anliantest.model.Task;
import cn.edu.tongji.anliantest.model.experiment.JCBGItem;
import cn.edu.tongji.anliantest.model.experiment.JCBGTable;
import cn.edu.tongji.anliantest.model.experiment.JGPJTable;
import cn.edu.tongji.anliantest.model.experiment.JSJGTable;
import cn.edu.tongji.anliantest.model.experiment.SYSYJLTable;
import cn.edu.tongji.anliantest.model.experiment.ZYBWHYSItem;
import cn.edu.tongji.anliantest.service.SYSYJLService;
import cn.edu.tongji.anliantest.util.ApplicationContextUtil;
import cn.edu.tongji.anliantest.util.DataWrapper;

@Service("sysyjlService")
public class SYSYJLServiceImpl implements SYSYJLService{
	private static final Logger logger = LoggerFactory.getLogger(SYSYJLServiceImpl.class);
	
	@Autowired
	private SYSYJLTableDao sysyjlTableDao;
	
	@Autowired
	private JCBGTableDao jcbgTableDao;
	
	@Autowired
	private JSJGTableDao jsjgTableDao;
	
	@Autowired
	private JGPJTableDao jgpjTableDao;
	
	@Autowired
	private JCTZDTableDao jctzdTableDao;
	
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
	public DataWrapper<SYSYJLTable> getSYSYJLTableById(Long sysyjlTableId) {
		DataWrapper<SYSYJLTable> ret = new DataWrapper<SYSYJLTable>();
		
		ret.setData(sysyjlTableDao.getSYSYJLTableById(sysyjlTableId));
		
		return ret;
	}

	@Override
	public DataWrapper<SYSYJLTable> getSYSYJLTableByProjectId(Long projectId) {
		DataWrapper<SYSYJLTable> ret = new DataWrapper<SYSYJLTable>();
		
		ret.setData(sysyjlTableDao.getSYSYJLTableByProjectId(projectId));
		
		return ret;
	}

	@Override
	public DataWrapper<SYSYJLTable> addSYSYJLTable(SYSYJLTable sysyjlTable,
			Long taskId, Long employeeId) {
		DataWrapper<SYSYJLTable> ret = new DataWrapper<SYSYJLTable>();
		
		Task createTask = taskDao.getTaskById(taskId);
		Project project = createTask.getProject();
		
		sysyjlTable.setProject(project);
		
		sysyjlTableDao.addSYSYJLTable(sysyjlTable);
		
		//完成新建送样、收样记录表的Task
		createTask.setStatus(false);
		taskDao.updateTask(createTask);
		
		//记录完成Task的employee
		Log createLog = new Log(employeeDao.getEmployeeById(employeeId), createTask);
		logDao.addLog(createLog);
		
		//为实验室总收样人创建确认送样、收样记录表的Task,同时更新项目状态
		project.setStep(ProjectStepEnum.STEP5);
		project.setStatus(ProjectStatusEnum.CONFIRM_SYSYJL);
		projectDao.updateProject(project);
		
		taskDao.addTask(new Task(project, ProjectStepEnum.STEP5, 
				ProjectStatusEnum.CONFIRM_SYSYJL, 
				employeeDao.getEmployeeById(sysyjlTable.getReceiveSampleEmployee().getId())));
		
		ret.setData(sysyjlTableDao.getSYSYJLTableById(sysyjlTable.getId()));
		
		logger.info("添加送样、收样记录单信息:"+sysyjlTable.getId());
		
		return ret;
	}

	@Override
	public DataWrapper<SYSYJLTable> updateSYSYJLTable(SYSYJLTable sysyjlTable,
			Long taskId, Long employeeId) {
		DataWrapper<SYSYJLTable> ret = new DataWrapper<SYSYJLTable>();
		
		Task confirmTask = taskDao.getTaskById(taskId);
		Project project = confirmTask.getProject();
		
		JCBGTable jcbgTable = new JCBGTable();
		jcbgTable.setProject(project);
		
		jcbgTable.setSampleStartDate(sysyjlTable.getSampleStartDate());
		jcbgTable.setSampleEndDate(sysyjlTable.getSampleEndDate());
		
		JCTZDTable jctzdTable = jctzdTableDao.getJCTZDTableByProjectId(project.getId());
		jcbgTable.setTestStartDate(jctzdTable.getTestStartDate());
		jcbgTable.setTestEndDate(jctzdTable.getTestEndDate());
		
		for(int day = 0; day < sysyjlTable.getItems().size(); day++) {
			Date sampleDate = sysyjlTable.getItems().get(day).getSampleDate();
			for(int i = 0; i < sysyjlTable.getItems().get(day).getItems().size(); i++) {
				String workshopPosition = sysyjlTable.getItems().get(day).getItems().get(i).getWorkshopPosition();
				for(int j = 0; j < sysyjlTable.getItems().get(day).getItems().get(i).getItems().size(); j++) {
					ZYBWHYSItem zybwhysItem = sysyjlTable.getItems().get(day).getItems().get(i).getItems().get(j).getZybwhysItem();
					String zybwhysItemDetailName = sysyjlTable.getItems().get(day).getItems().get(i).getItems().get(j).getZybwhysItemDetailName();
					for(int k = 0; k < sysyjlTable.getItems().get(day).getItems().get(i).getItems().get(j).getItems().size(); k++) {
						String sampleNumBase = sysyjlTable.getItems().get(day).getItems().get(i).getItems().get(j).getItems().get(k).getSampleNumBase();
						Integer sampleNumStart = sysyjlTable.getItems().get(day).getItems().get(i).getItems().get(j).getItems().get(k).getSampleNumStart();
						Integer sampleNumEnd = sysyjlTable.getItems().get(day).getItems().get(i).getItems().get(j).getItems().get(k).getSampleNumEnd();
						
						for(int p = sampleNumStart; p <= sampleNumEnd; p++) {
							JCBGItem jcbgItem = new JCBGItem();
							jcbgItem.setWorkshopPosition(workshopPosition);
							jcbgItem.setZybwhysItem(zybwhysItem);
							jcbgItem.setZybwhysItemDetailName(zybwhysItemDetailName);
							jcbgItem.setTestDate(sampleDate);
							
							String sampleNum = sampleNumBase + "-";
							if(p < 10) {
								sampleNum += "0" + p;
							}else{
								sampleNum += p;
							}
							jcbgItem.setSampleNum(sampleNum);
					
							jcbgTable.getItems().add(jcbgItem);
						}
					}
				}
			}
		}
		Collections.sort(jcbgTable.getItems());
		
		sysyjlTable.setConfirm(true);
		sysyjlTableDao.updateSYSYJLTable(sysyjlTable);
		
		JCBGTable oldJCBGTable = jcbgTableDao.getJCBGTableByProjectId(project.getId());
		JSJGTable oldJSJGTable = jsjgTableDao.getJSJGTableByProjectId(project.getId());
		JGPJTable oldJGPJTable = jgpjTableDao.getJGPJTableByProjectId(project.getId());
		if(oldJCBGTable != null) {
			jcbgTableDao.deleteJCBGTable(oldJCBGTable.getId());
		}
		if(oldJSJGTable != null) {
			jsjgTableDao.deleteJSJGTable(oldJSJGTable.getId());
		}
		if(oldJGPJTable != null) {
			jgpjTableDao.deleteJGPJTable(oldJGPJTable.getId());
		}
		jcbgTableDao.addJCBGTable(jcbgTable);
		
		//完成确认送样、收样记录表的Task
		confirmTask.setStatus(false);
		taskDao.updateTask(confirmTask);
		
		//记录完成Task的employee
		Log confirmLog = new Log(employeeDao.getEmployeeById(employeeId), confirmTask);
		logDao.addLog(confirmLog);
		
		//为检测部创建输入检测报告的Task,同时更新项目状态
		project.setStep(ProjectStepEnum.STEP5);
		project.setStatus(ProjectStatusEnum.INPUT_JCBG);
		projectDao.updateProject(project);
		
		taskDao.addTask(new Task(project, ProjectStepEnum.STEP5, 
				ProjectStatusEnum.INPUT_JCBG, 
				departmentDao.getDepartmentByType(DepartmentTypeEnum.DETECTION)));
		
		ret.setData(sysyjlTableDao.getSYSYJLTableById(sysyjlTable.getId()));
		
		logger.info("确认送样、收样记录单信息:"+sysyjlTable.getId());
		logger.info("添加检测报告信息:"+jcbgTable.getId());
		
		return ret;
	}

	@Override
	public DataWrapper<Void> deleteSYSYJLTable(Long sysyjlTableId) {
		return null;
	}

	@Override
	public File getSYSYJLFile(Long projectId) {
		SYSYJLTable sysyjlTable = sysyjlTableDao.getSYSYJLTableByProjectId(projectId);
		
		ServletContext context = ApplicationContextUtil.getContext().getServletContext();
		Project project = sysyjlTable.getProject();
		String filePath = context.getRealPath("report") + File.separator + project.getNumber() + File.separator + project.getNumber() + "-" + project.getName() + "-" + "送样、收样记录表" + ".doc";
		File file = new File(filePath);
		if(file.exists()){
			logger.info("下载" + project.getName() + "送样、收样记录表");
			return file;
		}
		else {
			SYSYJLDocument.generate(sysyjlTable.getId());
			file = new File(filePath);
			if(file.exists()) {
				logger.info("下载" + project.getName() + "送样、收样记录表");
				return file;
			}else{
				return null;
			}
		}
	}

}
