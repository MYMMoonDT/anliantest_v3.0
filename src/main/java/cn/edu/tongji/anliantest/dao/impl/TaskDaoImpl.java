package cn.edu.tongji.anliantest.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.tongji.anliantest.dao.AbstractHibernateDao;
import cn.edu.tongji.anliantest.dao.TaskDao;
import cn.edu.tongji.anliantest.model.Project;
import cn.edu.tongji.anliantest.model.ProjectStatusEnum;
import cn.edu.tongji.anliantest.model.ProjectStepEnum;
import cn.edu.tongji.anliantest.model.Task;

@Repository
public class TaskDaoImpl extends AbstractHibernateDao<Task, Long> implements TaskDao{

	protected TaskDaoImpl() {
		super(Task.class);
	}

	@Override
	public Task getTaskById(Long taskId) {
		return findById(taskId);
	}
	
	@Override
	public List<Task> getTaskByProjectInfo(Project project, ProjectStepEnum step,
			ProjectStatusEnum status) {
		List<Criterion> criterions = new ArrayList<Criterion>();
		
		criterions.add(Restrictions.eq("project.id", project.getId()));
		
		criterions.add(Restrictions.eq("projectStep", step));
		criterions.add(Restrictions.eq("projectStatus", status));
		criterions.add(Restrictions.eq("status", true));
		
		List<Task> list = findByCriteria(criterions);
		return list;
	}

	@Override
	public Long addTask(Task task) {
		return add(task);
	}

	@Override
	public void updateTask(Task task) {
		saveOrUpdate(task);
	}

	@Override
	public void deleteTask(Long taskId) {
		delete(findById(taskId));
	}

	@Override
	public List<Task> getTasksByEmployeeId(Long employeeId) {
		List<Criterion> criterions = new ArrayList<Criterion>();
		
		criterions.add(Restrictions.eq("employee.id", employeeId));
		criterions.add(Restrictions.eq("status", true));
		List<Task> list = findByCriteria(criterions);

		return list;
	}

	@Override
	public List<Task> getTasksByDepartmentId(Long departmentId) {
		List<Criterion> criterions = new ArrayList<Criterion>();
		
		criterions.add(Restrictions.eq("department.id", departmentId));
		criterions.add(Restrictions.eq("status", true));
		List<Task> list = findByCriteria(criterions);

		return list;
	}
	
}
