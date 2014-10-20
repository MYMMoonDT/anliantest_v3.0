package cn.edu.tongji.anliantest.service;

import java.util.List;

import cn.edu.tongji.anliantest.model.Task;
import cn.edu.tongji.anliantest.util.DataWrapper;

public interface TaskService {
	public DataWrapper<Task> getTaskById(Long taskId);
	
	public DataWrapper<Task> addTask(Task task);
	
	public DataWrapper<Task> updateTask(Task task);
	
	public DataWrapper<Void> deleteTask(Long taskId);
	
	public DataWrapper<List<Task>> getTaskListByEmployeeId(Long employeeId);
	
	public DataWrapper<List<Task>> getTaskListByDepartmentId(Long departmentId);
}
