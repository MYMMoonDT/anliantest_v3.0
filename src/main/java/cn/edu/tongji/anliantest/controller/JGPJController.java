package cn.edu.tongji.anliantest.controller;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.edu.tongji.anliantest.service.JGPJService;
import cn.edu.tongji.anliantest.util.FileUtil;

@Controller
@RequestMapping("api")
public class JGPJController {
	JGPJService jgpjService;
	
	@Autowired
	public JGPJController(JGPJService jgpjService) {
		this.jgpjService = jgpjService;
	}
	
	@RequestMapping(value="jgpj/download")
	public void downloadJGPJFile(
			HttpServletResponse response,
			 @RequestParam("projectId") Long projectId) {
		File file = jgpjService.getJGPJFile(projectId);
		try {
			FileUtil.downloadFile(file, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
