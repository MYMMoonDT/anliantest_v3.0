package cn.edu.tongji.anliantest.dao;

import java.util.List;

import cn.edu.tongji.anliantest.model.Employee;
import cn.edu.tongji.anliantest.util.DataWrapper;

public interface EmployeeDao {
	public Employee getEmployeeById(Long employeeId);
	
	public Employee getEmployeeByNum(String employeeNum);
	
	public Long addEmployee(Employee employee);
	
	public void updateEmployee(Employee employee);
	
	public void deleteEmployee(Long employeeId);
	
	public DataWrapper<List<Employee>> getEmployeeList(int currPageNum, int numPerPage);
}
