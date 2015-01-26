package cn.edu.tongji.anliantest.controller;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.tongji.anliantest.model.experiment.JSJGTable;
import cn.edu.tongji.anliantest.service.JSJGService;
import cn.edu.tongji.anliantest.util.DataWrapper;
import cn.edu.tongji.anliantest.util.FileUtil;

@Controller
@RequestMapping("api")
public class JSJGController {
	JSJGService jsjgService;
	
	@Autowired
	public JSJGController(JSJGService jsjgService) {
		this.jsjgService = jsjgService;
	}
	
	@RequestMapping(value="jsjg/download")
	public void downloadJSJGFile(HttpServletResponse response,
			@RequestParam("projectId") Long projectId) {
		File file = jsjgService.getJSJGTmpFile(projectId);
		try {
			FileUtil.downloadFile(file, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="jsjg/project", method=RequestMethod.GET)
	@ResponseBody
	public DataWrapper<JSJGTable> getJGPJByProject(
		@RequestParam("projectId") Long projectId) {
		return jsjgService.getJSJGTableByProjectId(projectId);
	}
}
