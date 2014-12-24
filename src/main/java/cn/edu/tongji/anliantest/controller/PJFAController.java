package cn.edu.tongji.anliantest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.tongji.anliantest.model.PJFA;
import cn.edu.tongji.anliantest.service.PJFAService;
import cn.edu.tongji.anliantest.util.DataWrapper;

@Controller
@RequestMapping("api")
public class PJFAController {
	PJFAService pjfaService;
	
	@Autowired
	public PJFAController(PJFAService pjfaService) {
		this.pjfaService = pjfaService;
	}
	
	@RequestMapping(value="pjfa", method=RequestMethod.POST)
	@ResponseBody
	public DataWrapper<PJFA> addPJFA(
		@RequestParam("taskId") Long taskId,
		@RequestParam("employeeId") Long employeeId,
		@RequestBody PJFA pjfa) {
		return pjfaService.addPJFA(pjfa, taskId, employeeId);
	}
	
	@RequestMapping(value="pjfa/project", method=RequestMethod.GET)
	@ResponseBody
	public DataWrapper<PJFA> getPJFAByProject(
		@RequestParam("projectId") Long projectId) {
		return pjfaService.getPJFAByProjectId(projectId);
	}
}
