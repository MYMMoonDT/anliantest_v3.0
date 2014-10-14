package cn.edu.tongji.anliantest.service;

import java.util.List;

import cn.edu.tongji.anliantest.model.Employee;
import cn.edu.tongji.anliantest.util.DataWrapper;

public interface EmployeeService {
	
	public DataWrapper<Employee> login(String number, String password);
	
	public DataWrapper<Void> logout();
	
	public DataWrapper<Employee> getEmployeeById(Long employeeId);
	
	public DataWrapper<Employee> addEmployee(Employee employee);
	
	public DataWrapper<Employee> updateEmployee(Employee employee);
	
	public DataWrapper<Void> deleteEmployee(Long employeeId);
	
	public DataWrapper<List<Employee>> getEmployeeList(int currPageNum, int numPerPage);
	
}
