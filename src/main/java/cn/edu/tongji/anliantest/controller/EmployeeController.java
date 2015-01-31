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
import cn.edu.tongji.anliantest.model.EmployeeAuthorityItem;
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

	@RequestMapping(value = "employee/login")
	@ResponseBody
	public DataWrapper<Employee> login(
			@RequestParam(value = "number") String number,
			@RequestParam(value = "password") String password) {
		return employeeService.login(number, password);
	}

	@RequestMapping(value = "employee", method = RequestMethod.GET)
	@ResponseBody
	public DataWrapper<List<Employee>> getEmployeeList(
			@RequestParam(value = "currPageNum") int currPageNum,
			@RequestParam(value = "numPerPage") int numPerPage) {
		return employeeService.getEmployeeList(currPageNum, numPerPage);
	}

	@RequestMapping(value = "employee/all", method = RequestMethod.GET)
	@ResponseBody
	public DataWrapper<List<Employee>> getAllEmployeeList() {
		return employeeService.getAllEmployeeList();
	}

	@RequestMapping(value = "employee/{employeeId}", method = RequestMethod.GET)
	@ResponseBody
	public DataWrapper<Employee> getEmployeeById(
			@PathVariable("employeeId") Long employeeId) {
		return employeeService.getEmployeeById(employeeId);
	}

	@RequestMapping(value = "employee", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Employee> addEmployee(@RequestBody Employee employee) {
		return employeeService.addEmployee(employee);
	}

	@RequestMapping(value = "employee", method = RequestMethod.PUT)
	@ResponseBody
	public DataWrapper<Employee> updateEmployee(@RequestBody Employee employee) {
		return employeeService.updateEmployee(employee);
	}

	@RequestMapping(value = "employee/{employeeId}", method = RequestMethod.DELETE)
	@ResponseBody
	public DataWrapper<Void> deleteEmployee(
			@PathVariable("employeeId") Long employeeId) {
		return employeeService.deleteEmployee(employeeId);
	}
	
	@RequestMapping(value = "employee/authorityGroup/update", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Employee> updateEmployeeAuthorityGroup(
			@RequestParam("employeeId") Long employeeId,
			@RequestParam("authorityGroupId") Long authorityGroupId) {
		return employeeService.updateEmployeeAuthorityGroup(employeeId, authorityGroupId);
	}
	
	@RequestMapping(value = "employee/authorityItem/update", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> updateEmployeeAuthorityItem(
			@RequestBody EmployeeAuthorityItem employeeAuthorityItem) {
		return employeeService.updateEmployeeAuthorityItem(employeeAuthorityItem);
	}
}
