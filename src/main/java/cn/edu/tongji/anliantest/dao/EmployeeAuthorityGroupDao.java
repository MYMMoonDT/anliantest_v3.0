package cn.edu.tongji.anliantest.dao;

import java.util.List;

import cn.edu.tongji.anliantest.model.EmployeeAuthorityGroup;
import cn.edu.tongji.anliantest.model.Department;
import cn.edu.tongji.anliantest.util.DataWrapper;

public interface EmployeeAuthorityGroupDao {
public EmployeeAuthorityGroup getEmployeeAuthorityGroupById(Long employeeEmployeeAuthorityGroupId);
	
	public EmployeeAuthorityGroup getEmployeeAuthorityGroupByDepartment(Department department);
	
	public Long addEmployeeAuthorityGroup(EmployeeAuthorityGroup employeeEmployeeAuthorityGroup);
	
	public EmployeeAuthorityGroup updateEmployeeAuthorityGroup(EmployeeAuthorityGroup employeeEmployeeAuthorityGroup);
	
	public void deleteEmployeeAuthorityGroup(Long employeeEmployeeEmployeeAuthorityGroupId);

	public DataWrapper<List<EmployeeAuthorityGroup>> getAllEmployeeAuthorityGroups();
	
	public DataWrapper<List<EmployeeAuthorityGroup>> getEmployeeAuthorityGroupsByAuthorityGroupId(Long authorityGroupId);
}

