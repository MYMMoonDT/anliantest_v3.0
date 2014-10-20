package cn.edu.tongji.anliantest.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.tongji.anliantest.dao.TaskDao;
import cn.edu.tongji.anliantest.model.Task;
import cn.edu.tongji.anliantest.service.TaskService;
import cn.edu.tongji.anliantest.util.DataWrapper;

@Service("taskService")
public class TaskServiceImpl implements TaskService{

	private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);
	
	@Autowired
	private TaskDao taskDao;
	
	@Override
	public DataWrapper<Task> getTaskById(Long taskId) {
		return getTaskById(taskId);
	}

	@Override
	public DataWrapper<Task> addTask(Task task) {
		DataWrapper<Task> ret = new DataWrapper<>();
		
		taskDao.addTask(task);
		
		ret.setData(taskDao.getTaskById(task.getId()));
		
		logger.info("添加任务信息:"+task.getId());
		
		return ret;
	}

	@Override
	public DataWrapper<Task> updateTask(Task task) {
		DataWrapper<Task> ret = new DataWrapper<>();
		
		taskDao.updateTask(task);
		
		ret.setData(taskDao.getTaskById(task.getId()));
		
		logger.info("更新任务信息:"+task.getId());
		
		return ret;
	}

	@Override
	public DataWrapper<Void> deleteTask(Long taskId) {
		DataWrapper<Void> ret = new DataWrapper<>();
		
		taskDao.deleteTask(taskId);
		
		logger.info("删除任务信息:"+taskId);
		
		return ret;
	}

	@Override
	public DataWrapper<List<Task>> getTaskListByEmployeeId(Long employeeId) {
		DataWrapper<List<Task>> ret = new DataWrapper<>();
		
		ret.setData(taskDao.getTasksByEmployeeId(employeeId));
		
		return ret;
	}

	@Override
	public DataWrapper<List<Task>> getTaskListByDepartmentId(Long departmentId) {
		DataWrapper<List<Task>> ret = new DataWrapper<>();
		
		ret.setData(taskDao.getTasksByDepartmentId(departmentId));
		
		return ret;
	}
	
}
