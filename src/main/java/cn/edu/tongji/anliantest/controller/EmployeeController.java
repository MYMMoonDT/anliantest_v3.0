package cn.edu.tongji.anliantest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.tongji.anliantest.service.EmployeeService;

@Controller
@RequestMapping("api")
public class EmployeeController {
	EmployeeService employeeService;
	
	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	
}
