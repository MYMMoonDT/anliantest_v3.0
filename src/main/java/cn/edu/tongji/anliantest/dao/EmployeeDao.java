package cn.edu.tongji.anliantest.dao;

import cn.edu.tongji.anliantest.model.Employee;

public interface EmployeeDao {
	public Employee getEmployeeById(Long employeeId);
	
	public Employee getEmployeeByNum(String employeeNum);
	
	public Long addEmployee(Employee employee);
	
	public void updateEmployee(Employee employee);
	
	public void deleteEmployee(Long employeeId);
}
