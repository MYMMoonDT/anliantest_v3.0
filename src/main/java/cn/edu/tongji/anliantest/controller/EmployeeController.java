package cn.edu.tongji.anliantest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.tongji.anliantest.model.Employee;
import cn.edu.tongji.anliantest.service.EmployeeService;
import cn.edu.tongji.anliantest.util.DataWrapper;

@Controller
@RequestMapping("api")
public class EmployeeController {
	EmployeeService employeeService;
	
	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@RequestMapping(value="employee", method=RequestMethod.GET)
	@ResponseBody
	public DataWrapper<List<Employee>> getEmployeeList(
		@RequestParam(value = "currPageNum") int currPageNum,
		@RequestParam(value = "numPerPage") int numPerPage) {
		return employeeService.getEmployeeList(currPageNum, numPerPage);
	}
	
	@RequestMapping(value="employee/{employeeId}", method=RequestMethod.GET)
    @ResponseBody
    public DataWrapper<Employee> getEmployeeById(
		@PathVariable("employeeId") Long employeeId) {
		return employeeService.getEmployeeById(employeeId);
	}
	
	@RequestMapping(value="employee", method=RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Employee> addEmployee(
		@RequestBody Employee employee) {
		return employeeService.addEmployee(employee);
	}
	
	@RequestMapping(value="employee/{employeeId}", method=RequestMethod.PUT)
	@ResponseBody
	public DataWrapper<Employee> updateEmployee(
		@RequestBody Employee employee) {
		return employeeService.updateEmployee(employee);
	}
}
