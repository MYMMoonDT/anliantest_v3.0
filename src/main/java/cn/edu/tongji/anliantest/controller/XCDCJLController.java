package cn.edu.tongji.anliantest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.tongji.anliantest.model.XCDCJLTable;
import cn.edu.tongji.anliantest.service.XCDCJLService;
import cn.edu.tongji.anliantest.util.DataWrapper;

@Controller
@RequestMapping("api")
public class XCDCJLController {
	XCDCJLService xcdcjlService;
	
	@Autowired
	public XCDCJLController(XCDCJLService xcdcjlService) {
		this.xcdcjlService = xcdcjlService;
	}
	
	@RequestMapping(value="xcdcjl", method=RequestMethod.POST)
	@ResponseBody
	public DataWrapper<XCDCJLTable> addXCDCJL(
		@RequestParam("taskId") Long taskId,
		@RequestParam("employeeId") Long employeeId,
		@RequestBody XCDCJLTable xcdcjlTable) {
		return xcdcjlService.addXCDCJL(xcdcjlTable, taskId, employeeId);
	}
	
	@RequestMapping(value="xcdcjl/project", method=RequestMethod.GET)
	@ResponseBody
	public DataWrapper<XCDCJLTable> getXCDCJLByProject(
		@RequestParam("projectId") Long projectId) {
		return xcdcjlService.getXCDCJLByProject(projectId);
	}
}
