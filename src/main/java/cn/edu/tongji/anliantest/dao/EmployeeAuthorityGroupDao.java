package cn.edu.tongji.anliantest.dao;

import cn.edu.tongji.anliantest.model.EmployeeAuthorityGroup;

public interface EmployeeAuthorityGroupDao {
	public EmployeeAuthorityGroup getEmployeeAuthorityGroupById(Long employeeAuthorityGroupById);
	
	public Long addEmployeeAuthorityGroup(EmployeeAuthorityGroup employeeAuthorityGroup);
	
	public void updateEmployeeAuthorityGroup(EmployeeAuthorityGroup employeeAuthorityGroup);
	
	public void deleteEmployeeAuthorityGroup(Long employeeAuthorityGroupById);
}
