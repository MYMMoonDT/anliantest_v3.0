package cn.edu.tongji.anliantest.controller;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.tongji.anliantest.model.experiment.JGPJTable;
import cn.edu.tongji.anliantest.service.JGPJService;
import cn.edu.tongji.anliantest.util.DataWrapper;
import cn.edu.tongji.anliantest.util.FileUtil;

@Controller
@RequestMapping("api")
public class JGPJController {
	JGPJService jgpjService;
	
	@Autowired
	public JGPJController(JGPJService jgpjService) {
		this.jgpjService = jgpjService;
	}
	
	@RequestMapping(value="jgpj/tmp/download")
	public void downloadJGPJTmpFile(HttpServletResponse response,
			@RequestParam("projectId") Long projectId) {
		File file = jgpjService.getJGPJTmpFile(projectId);
		try {
			FileUtil.downloadFile(file, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="jgpj/download")
	public void downloadJGPJFile(HttpServletResponse response,
			@RequestParam("projectId") Long projectId) {
		File file = jgpjService.getJGPJFile(projectId);
		try {
			FileUtil.downloadFile(file, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="jgpj/project", method=RequestMethod.GET)
	@ResponseBody
	public DataWrapper<JGPJTable> getJGPJByProject(
		@RequestParam("projectId") Long projectId) {
		return jgpjService.getJGPJTableByProjectId(projectId);
	}
}
