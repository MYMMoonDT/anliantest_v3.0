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

import cn.edu.tongji.anliantest.model.JCTZDTable;
import cn.edu.tongji.anliantest.service.JCTZDService;
import cn.edu.tongji.anliantest.util.DataWrapper;
import cn.edu.tongji.anliantest.util.FileUtil;

@Controller
@RequestMapping("api")
public class JCTZDController {
	JCTZDService jctzdService;
	
	@Autowired
	public JCTZDController(JCTZDService jctzdService){
		this.jctzdService = jctzdService;
	}
	
	@RequestMapping(value="jctzd", method=RequestMethod.POST)
	@ResponseBody
	public DataWrapper<JCTZDTable> addJCTZD(
		@RequestParam("taskId") Long taskId,
		@RequestParam("employeeId") Long employeeId,
		@RequestBody JCTZDTable jctzdTable) {
		return jctzdService.addJCTZDTable(jctzdTable, taskId, employeeId);
	}
	
	@RequestMapping(value="jctzd/project", method=RequestMethod.GET)
	@ResponseBody
	public DataWrapper<JCTZDTable> getJCTZDByProject(
		@RequestParam("projectId") Long projectId) {
		return jctzdService.getJCTZDTableProjectId(projectId);
	}
	
	@RequestMapping(value="jctzd/download")
	public void downloadJCTZDFile(HttpServletResponse response,
			@RequestParam("projectId") Long projectId) {
		File file = jctzdService.getJCTZDFile(projectId);
		try {
			FileUtil.downloadFile(file, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
