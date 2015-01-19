package cn.edu.tongji.anliantest.service.impl;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.tongji.anliantest.dao.CYFATableDao;
import cn.edu.tongji.anliantest.dao.DepartmentDao;
import cn.edu.tongji.anliantest.dao.EmployeeDao;
import cn.edu.tongji.anliantest.dao.JCTZDTableDao;
import cn.edu.tongji.anliantest.dao.LogDao;
import cn.edu.tongji.anliantest.dao.ProjectDao;
import cn.edu.tongji.anliantest.dao.TaskDao;
import cn.edu.tongji.anliantest.model.DepartmentTypeEnum;
import cn.edu.tongji.anliantest.model.JCTZDGroup;
import cn.edu.tongji.anliantest.model.JCTZDItem;
import cn.edu.tongji.anliantest.model.JCTZDTable;
import cn.edu.tongji.anliantest.model.Log;
import cn.edu.tongji.anliantest.model.Project;
import cn.edu.tongji.anliantest.model.ProjectStatusEnum;
import cn.edu.tongji.anliantest.model.ProjectStepEnum;
import cn.edu.tongji.anliantest.model.Task;
import cn.edu.tongji.anliantest.model.experiment.CYFAGroup;
import cn.edu.tongji.anliantest.model.experiment.CYFAItem;
import cn.edu.tongji.anliantest.model.experiment.CYFASubItem;
import cn.edu.tongji.anliantest.model.experiment.CYFATable;
import cn.edu.tongji.anliantest.model.experiment.CYJCFFItem;
import cn.edu.tongji.anliantest.model.experiment.ZYBWHYSItem;
import cn.edu.tongji.anliantest.service.JCTZDService;
import cn.edu.tongji.anliantest.util.CallStatusEnum;
import cn.edu.tongji.anliantest.util.DataWrapper;
import cn.edu.tongji.anliantest.util.ErrorCodeEnum;
import cn.edu.tongji.anliantest.util.SampleNum;

@Service("jctzdService")
public class JCTZDServiceImpl implements JCTZDService{
	private static final Logger logger = LoggerFactory.getLogger(JCTZDServiceImpl.class);
	
	@Autowired
	public JCTZDTableDao jctzdTableDao;
	
	@Autowired
	public CYFATableDao cyfaTableDao;
	
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
	public DataWrapper<JCTZDTable> getJCTZDTableById(Long jctzdTableId) {
		DataWrapper<JCTZDTable> ret = new DataWrapper<JCTZDTable>();
		
		ret.setData(jctzdTableDao.getJCTZDTableById(jctzdTableId));
		
		return ret;
	}

	@Override
	public DataWrapper<JCTZDTable> getJCTZDTableProjectId(Long projectId) {
		DataWrapper<JCTZDTable> ret = new DataWrapper<JCTZDTable>();
		
		ret.setData(jctzdTableDao.getJCTZDTableByProjectId(projectId));
		
		return ret;
	}

	@Override
	public DataWrapper<JCTZDTable> addJCTZDTable(JCTZDTable jctzdTable,
			Long taskId, Long employeeId) {
		String errorMsg = "";
		
		DataWrapper<JCTZDTable> ret = new DataWrapper<JCTZDTable>();
		
		Task createTask = taskDao.getTaskById(taskId);
		Project project = createTask.getProject();
		
		//根据检测通知单生成采样方案
		CYFATable cyfaTable = new CYFATable();
		cyfaTable.setProject(project);
		
		try {
			for(int i = 0; i < jctzdTable.getItems().size(); i++) {
				int sampleIndex = 1;
				CYFAGroup cyfaGroup = new CYFAGroup();
				JCTZDGroup jctzdGroup = jctzdTable.getItems().get(i);
				
				cyfaGroup.setWorkshopPosition(jctzdGroup.getWorkshopPosition());
				for(int j = 0; j < jctzdGroup.getItems().size(); j++) {
					CYFAItem cyfaItem = new CYFAItem();
					JCTZDItem jctzdItem = jctzdGroup.getItems().get(j);
					ZYBWHYSItem zybwhysItem = jctzdItem.getZybwhysItem();
					cyfaItem.setZybwhysItem(zybwhysItem);
					cyfaItem.setZybwhysItemDetailName(jctzdItem.getZybwhysItemDetailName());
					cyfaItem.setSampleCount(jctzdItem.getSampleCount());
					
					if(zybwhysItem.getItems().size() == 0) {
						errorMsg = "没有找到["+ zybwhysItem.getChineseName() +"]对应的采样方法";
						throw new Exception();
					}
					
					Iterator<CYJCFFItem> iter = zybwhysItem.getItems().iterator();
					int index = 0;
					while (iter.hasNext()) {  
						CYFASubItem cyfaSubItem = new CYFASubItem();
						cyfaSubItem.setCyjcffItem(iter.next());
						String sampleNumBase = SampleNum.getSampleNumBaseFromProjectNum(project.getNumber());
						if(i < 9) {
							sampleNumBase = sampleNumBase + "0" + (i+1);
						}else{
							sampleNumBase = sampleNumBase + (i+1);
						}
						cyfaSubItem.setSampleNumBase(sampleNumBase);
						if(index == 0) {
							cyfaSubItem.setSampleCount(jctzdItem.getSampleCount());
							cyfaSubItem.setSampleNumStart(sampleIndex);
							cyfaSubItem.setSampleNumEnd(sampleIndex + jctzdItem.getSampleCount() - 1);
							sampleIndex += jctzdItem.getSampleCount();
						}else{
							cyfaSubItem.setSampleCount(0);
						}
						cyfaItem.getItems().add(cyfaSubItem);
						index++;
					}
					cyfaGroup.getItems().add(cyfaItem);
				}
				cyfaTable.getItems().add(cyfaGroup);
			}
		}catch(Exception e) {
			
			ret.setCallStatus(CallStatusEnum.FAILED);
			ret.setErrorCode(ErrorCodeEnum.ZYBWHYS_Not_Exist);
			ret.setErrorMsg("错误：" + errorMsg);
			
			return ret;
		}
		
		jctzdTableDao.addJCTZDTable(jctzdTable);
		cyfaTableDao.addCYFATable(cyfaTable);
		
		//完成新建检测通知单的Task
		createTask.setStatus(false);
		taskDao.updateTask(createTask);
		
		//记录完成Task的employee
		Log createLog = new Log(employeeDao.getEmployeeById(employeeId), createTask);
		logDao.addLog(createLog);
		
		//为检测部创建确认采样方案的Task,同时更新项目状态
		project.setStep(ProjectStepEnum.STEP4);
		project.setStatus(ProjectStatusEnum.CONFIRM_CYFA);
		projectDao.updateProject(project);
		
		taskDao.addTask(new Task(project, ProjectStepEnum.STEP4, 
				ProjectStatusEnum.CONFIRM_CYFA, 
				departmentDao.getDepartmentByType(DepartmentTypeEnum.DETECTION)));
		
		ret.setData(jctzdTableDao.getJCTZDTableById(jctzdTable.getId()));
		
		logger.info("添加检测通知单信息:"+jctzdTable.getId());
		logger.info("生成采样方案信息:"+cyfaTable.getId());
		
		return ret;
	}

	@Override
	public DataWrapper<JCTZDTable> updateJCTZDTable(JCTZDTable jctzdTable) {
		return null;
	}

	@Override
	public DataWrapper<Void> deleteJCTZDTable(Long jctzdTableId) {
		return null;
	}
	
}
