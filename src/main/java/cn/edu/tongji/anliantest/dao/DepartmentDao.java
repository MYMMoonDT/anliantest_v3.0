package cn.edu.tongji.anliantest.dao;

import cn.edu.tongji.anliantest.model.Department;
import cn.edu.tongji.anliantest.model.DepartmentTypeEnum;

public interface DepartmentDao {
	public Department getDepartmentById(Long departmentId);
	
	public Department getDepartmentByType(DepartmentTypeEnum departmentType);
	
	public Long addDepartment(Department department);
	
	public void updateDepartment(Department department);
	
	public void deleteDepartment(Long departmentId);
}
