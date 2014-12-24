package cn.edu.tongji.anliantest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.tongji.anliantest.model.KHZLDJDTable;
import cn.edu.tongji.anliantest.service.KHZLDJDService;
import cn.edu.tongji.anliantest.util.DataWrapper;

@Controller
@RequestMapping("api")
public class KHZLDJDController{
	KHZLDJDService khzldjdService;
	
	@Autowired
	public KHZLDJDController(KHZLDJDService khzldjdService) {
		this.khzldjdService = khzldjdService;
	}
	
	@RequestMapping(value="khzldjd", method=RequestMethod.POST)
	@ResponseBody
	public DataWrapper<KHZLDJDTable> addKHZLDJD(
		@RequestParam("taskId") Long taskId,
		@RequestParam("employeeId") Long employeeId,
		@RequestBody KHZLDJDTable khzldjdTable) {
		return khzldjdService.addKHZLDJD(khzldjdTable, taskId, employeeId);
	}
	
	@RequestMapping(value="khzldjd/project", method=RequestMethod.GET)
	@ResponseBody
	public DataWrapper<KHZLDJDTable> getKHZLDJDByProject(
		@RequestParam("projectId") Long projectId) {
		return khzldjdService.getKHZLDJDByProject(projectId);
	}
}
