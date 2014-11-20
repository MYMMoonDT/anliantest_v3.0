package cn.edu.tongji.anliantest.controller;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.edu.tongji.anliantest.service.JSJGService;
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
	public void downloadJSJGFile(
			 HttpServletResponse response,
			 @RequestParam("projectId") Long projectId) {
		File file = jsjgService.getJSJGFile(projectId);
		try {
			FileUtil.downloadFile(file, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
