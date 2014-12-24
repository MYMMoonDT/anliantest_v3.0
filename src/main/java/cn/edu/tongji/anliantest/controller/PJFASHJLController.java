package cn.edu.tongji.anliantest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.tongji.anliantest.model.PJFASHJLItem;
import cn.edu.tongji.anliantest.model.PJFASHJLTable;
import cn.edu.tongji.anliantest.service.PJFASHJLService;
import cn.edu.tongji.anliantest.util.DataWrapper;

@Controller
@RequestMapping("api")
public class PJFASHJLController {
	PJFASHJLService pjfaSHJLService;
	
	@Autowired
	public PJFASHJLController(PJFASHJLService pjfaSHJLService) {
		this.pjfaSHJLService = pjfaSHJLService;
	}
	
	@RequestMapping(value="pjfashjl", method=RequestMethod.POST)
	@ResponseBody
	public DataWrapper<PJFASHJLTable> addPJFASHJL(
		@RequestParam("taskId") Long taskId,
		@RequestParam("employeeId") Long employeeId,
		@RequestBody PJFASHJLTable pjfashjlTable) {
		return pjfaSHJLService.addPJFASHJL(pjfashjlTable, taskId, employeeId);
	}
	
	@RequestMapping(value="pjfashjl/project", method=RequestMethod.GET)
	@ResponseBody
	public DataWrapper<PJFASHJLTable> getPJFASHJLByProject(
		@RequestParam("projectId") Long projectId) {
		return pjfaSHJLService.getPJFASHJLByProjectId(projectId);
	}
	
	@RequestMapping(value="pjfashjl/sign", method=RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> signPJFASHJL(
		@RequestParam("taskId") Long taskId,
		@RequestParam("employeeId") Long employeeId,
		@RequestBody PJFASHJLItem pjfashjlItem) {
		return pjfaSHJLService.signPJFASHJL(pjfashjlItem, taskId, employeeId);
	}
}
