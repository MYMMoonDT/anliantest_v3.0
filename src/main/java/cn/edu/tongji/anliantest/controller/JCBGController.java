package cn.edu.tongji.anliantest.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.tongji.anliantest.model.experiment.JCBGTable;
import cn.edu.tongji.anliantest.model.experiment.JCBGTableInput;
import cn.edu.tongji.anliantest.service.JCBGService;
import cn.edu.tongji.anliantest.util.DataWrapper;
import cn.edu.tongji.anliantest.util.FileUtil;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Controller
@RequestMapping("api")
public class JCBGController {
	JCBGService jcbgService;
	
	@Autowired
	public JCBGController(JCBGService jcbgService) {
		this.jcbgService = jcbgService;
	}
	
	@RequestMapping(value="jcbg/project", method=RequestMethod.GET)
	@ResponseBody
	public DataWrapper<JCBGTable> getJCBGByProject(
		@RequestParam("projectId") Long projectId) {
		return jcbgService.getJCBGTableByProjectId(projectId);
	}
	
	@RequestMapping(value="jcbg", method=RequestMethod.POST)
	@ResponseBody
	public DataWrapper<JCBGTable> addJCBGTable(
			@RequestParam("taskId") Long taskId,
			@RequestParam("employeeId") Long employeeId,
			@RequestBody JCBGTableInput jcbgTable) {
		return jcbgService.addJCBGTable(taskId, employeeId, jcbgTable);
	}
	
	@RequestMapping(value="jcbg/upload", method=RequestMethod.POST)
	@ResponseBody
	public DataWrapper<JCBGTable> uploadJCBGFile(
			 @RequestParam("file") MultipartFile file,
			 @RequestParam("projectId") Long projectId) {
		return jcbgService.uploadJCBGTable(projectId, file);
	}
	
	@RequestMapping(value="jcbg/input", method=RequestMethod.POST)
	@ResponseBody
	public DataWrapper<JCBGTable> inputJCBGTable(
			 @RequestBody JCBGTableInput jcbgTable,
			 @RequestParam("projectId") Long projectId) throws JsonParseException, JsonMappingException, IOException {		
		return jcbgService.inputJCBGTable(projectId, jcbgTable);
	}
	
	@RequestMapping(value="jcbg/download")
	public void downloadJCBGFile(HttpServletResponse response,
			@RequestParam("projectId") Long projectId) {
		File file = jcbgService.getJCBGFile(projectId);
		try {
			FileUtil.downloadFile(file, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
