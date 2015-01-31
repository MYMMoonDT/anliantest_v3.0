package cn.edu.tongji.anliantest.dao;

import cn.edu.tongji.anliantest.model.EmployeeAuthorityItem;

public interface EmployeeAuthorityItemDao {
	public EmployeeAuthorityItem getEmployeeAuthorityItemById(Long employeeAuthorityItemById);
	
	public Long addEmployeeAuthorityItem(EmployeeAuthorityItem employeeAuthorityItem);
	
	public void updateEmployeeAuthorityItem(EmployeeAuthorityItem employeeAuthorityItem);
	
	public void deleteEmployeeAuthorityItem(Long employeeAuthorityItemById);
}
