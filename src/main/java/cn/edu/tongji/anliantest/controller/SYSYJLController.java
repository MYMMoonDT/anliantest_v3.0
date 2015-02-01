package cn.edu.tongji.anliantest.controller;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.tongji.anliantest.model.experiment.SYSYJLTable;
import cn.edu.tongji.anliantest.service.SYSYJLService;
import cn.edu.tongji.anliantest.util.DataWrapper;
import cn.edu.tongji.anliantest.util.FileUtil;

@Controller
@RequestMapping("api")
public class SYSYJLController {
	SYSYJLService sysyjlService;
	
	@Autowired
	public SYSYJLController(SYSYJLService sysyjlService) {
		this.sysyjlService = sysyjlService;
	}
	
	@RequestMapping(value="sysyjl", method=RequestMethod.POST)
	@ResponseBody
	public DataWrapper<SYSYJLTable> addSYSYJL(
		@RequestParam("taskId") Long taskId,
		@RequestParam("employeeId") Long employeeId,
		@RequestBody SYSYJLTable sysyjlTable) {
		return sysyjlService.addSYSYJLTable(sysyjlTable, taskId, employeeId);
	}
	
	@RequestMapping(value="sysyjl/confirm", method=RequestMethod.POST)
	@ResponseBody
	public DataWrapper<SYSYJLTable> confirmSYSYJL(
		@RequestParam("taskId") Long taskId,
		@RequestParam("employeeId") Long employeeId,
		@RequestBody SYSYJLTable sysyjlTable) {
		return sysyjlService.updateSYSYJLTable(sysyjlTable, taskId, employeeId);
	}
	
	@RequestMapping(value="sysyjl/project", method=RequestMethod.GET)
	@ResponseBody
	public DataWrapper<SYSYJLTable> getSYSYJLByProject(
		@RequestParam("projectId") Long projectId) {
		return sysyjlService.getSYSYJLTableByProjectId(projectId);
	}
	
	@RequestMapping(value="sysyjl/download")
	public void downloadSYSYJLFile(HttpServletResponse response,
			@RequestParam("projectId") Long projectId) {
		File file = sysyjlService.getSYSYJLFile(projectId);
		try {
			FileUtil.downloadFile(file, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
