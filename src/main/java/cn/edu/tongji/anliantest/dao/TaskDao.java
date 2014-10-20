package cn.edu.tongji.anliantest.dao;

import java.util.List;

import cn.edu.tongji.anliantest.model.Project;
import cn.edu.tongji.anliantest.model.ProjectStatusEnum;
import cn.edu.tongji.anliantest.model.ProjectStepEnum;
import cn.edu.tongji.anliantest.model.Task;

public interface TaskDao {
	public Task getTaskById(Long taskId);
	
	public List<Task> getTaskByProjectInfo(Project project, ProjectStepEnum step, ProjectStatusEnum status);
	
	public Long addTask(Task task);
	
	public void updateTask(Task task);
	
	public void deleteTask(Long taskId);
	
	public List<Task> getTasksByEmployeeId(Long employeeId);
	
	public List<Task> getTasksByDepartmentId(Long departmentId);
}
