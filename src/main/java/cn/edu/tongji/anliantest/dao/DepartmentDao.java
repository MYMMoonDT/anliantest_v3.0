package cn.edu.tongji.anliantest.dao;

import java.util.List;

import cn.edu.tongji.anliantest.model.Department;
import cn.edu.tongji.anliantest.model.DepartmentTypeEnum;
import cn.edu.tongji.anliantest.util.DataWrapper;

public interface DepartmentDao {
	public Department getDepartmentById(Long departmentId);
	
	public Department getDepartmentByType(DepartmentTypeEnum departmentType);
	
	public Long addDepartment(Department department);
	
	public void updateDepartment(Department department);
	
	public void deleteDepartment(Long departmentId);

	public DataWrapper<List<Department>> getAllDepartments();
}
