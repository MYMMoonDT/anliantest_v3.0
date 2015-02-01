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

import cn.edu.tongji.anliantest.model.experiment.CYFATable;
import cn.edu.tongji.anliantest.service.CYFAService;
import cn.edu.tongji.anliantest.util.DataWrapper;
import cn.edu.tongji.anliantest.util.FileUtil;

@Controller
@RequestMapping("api")
public class CYFAController {
	CYFAService cyfaService;
	
	@Autowired
	public CYFAController(CYFAService cyfaService) {
		this.cyfaService = cyfaService;
	}
	
	@RequestMapping(value="cyfa/project", method=RequestMethod.GET)
	@ResponseBody
	public DataWrapper<CYFATable> getCYFAByProject(
		@RequestParam("projectId") Long projectId) {
		return cyfaService.getCYFATableProjectId(projectId);
	}
	
	@RequestMapping(value="cyfa/confirm", method=RequestMethod.POST)
	@ResponseBody
	public DataWrapper<CYFATable> confirmCYFA(
		@RequestParam("taskId") Long taskId,
		@RequestParam("employeeId") Long employeeId,
		@RequestBody CYFATable cyfaTable) {
		return cyfaService.confirmCYFATable(cyfaTable, taskId, employeeId);
	}
	
	@RequestMapping(value="cyfa/download")
	public void downloadCYFAFile(HttpServletResponse response,
			@RequestParam("projectId") Long projectId) {
		File file = cyfaService.getCYFAFile(projectId);
		try {
			FileUtil.downloadFile(file, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
