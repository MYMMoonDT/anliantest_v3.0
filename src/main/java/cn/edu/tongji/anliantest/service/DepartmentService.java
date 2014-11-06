package cn.edu.tongji.anliantest.service;

import java.util.List;

import cn.edu.tongji.anliantest.model.Department;
import cn.edu.tongji.anliantest.util.DataWrapper;

public interface DepartmentService {
	
	public DataWrapper<Department> getDepartmentById(Long departmentId);
	
	public DataWrapper<Department> addDepartment(Department department);
	
	public DataWrapper<Department> updateDepartment(Department department);
	
	public DataWrapper<Void> deleteDepartment(Long departmentId);
	
	public DataWrapper<List<Department>> getDepartmentList(int currPageNum, int numPerPage);

	public DataWrapper<List<Department>> getAllDepartments();	
}
