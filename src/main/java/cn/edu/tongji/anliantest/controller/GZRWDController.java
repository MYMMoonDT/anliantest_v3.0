package cn.edu.tongji.anliantest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.tongji.anliantest.model.GZRWDTable;
import cn.edu.tongji.anliantest.service.GZRWDService;
import cn.edu.tongji.anliantest.util.DataWrapper;

@Controller
@RequestMapping("api")
public class GZRWDController {
	GZRWDService gzrwdService;
	
	@Autowired
	public GZRWDController(GZRWDService gzrwdService) {
		this.gzrwdService = gzrwdService;
	}
	
	@RequestMapping(value="gzrwd", method=RequestMethod.POST)
	@ResponseBody
	public DataWrapper<GZRWDTable> addGZRWD(
		@RequestParam("taskId") Long taskId,
		@RequestParam("employeeId") Long employeeId,
		@RequestBody GZRWDTable gzrwdTable) {
		return gzrwdService.addGZRWDTable(gzrwdTable, taskId, employeeId);
	}
}
