package cn.edu.tongji.anliantest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.tongji.anliantest.model.Department;
import cn.edu.tongji.anliantest.service.DepartmentService;
import cn.edu.tongji.anliantest.util.DataWrapper;

@Controller
@RequestMapping("api")
public class DepartmentController {
DepartmentService departmentService;
	
	@Autowired
	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	@RequestMapping(value="department/{departmentId}", method=RequestMethod.GET)
    @ResponseBody
    public DataWrapper<Department> getDepartmentById(
		@PathVariable("departmentId") Long departmentId) {
		return departmentService.getDepartmentById(departmentId);
	}
	
	@RequestMapping(value="department", method=RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Department> addDepartment(
		@RequestBody Department department) {
		return departmentService.addDepartment(department);
	}
	
	@RequestMapping(value="department", method=RequestMethod.PUT)
	@ResponseBody
	public DataWrapper<Department> updateDepartment(
		@RequestBody Department department) {
		return departmentService.updateDepartment(department);
	}
	
	@RequestMapping(value="department/{departmentId}", method=RequestMethod.DELETE)
	@ResponseBody
	public DataWrapper<Void> deleteDepartment(
		@PathVariable("departmentId") Long departmentId) {
		return departmentService.deleteDepartment(departmentId);
	}
	
	@RequestMapping(value="department/all", method=RequestMethod.GET)
	@ResponseBody
	public DataWrapper<List<Department>> getAllDepartments() {
		return departmentService.getAllDepartments();
	}
}
