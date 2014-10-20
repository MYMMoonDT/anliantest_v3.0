package cn.edu.tongji.anliantest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.tongji.anliantest.model.HTPSJLTable;
import cn.edu.tongji.anliantest.service.HTPSJLService;
import cn.edu.tongji.anliantest.util.DataWrapper;

@Controller
@RequestMapping("api")
public class HTPSJLController {
	HTPSJLService htpsjlService;
	
	@Autowired
	public HTPSJLController(HTPSJLService htpsjlService) {
		this.htpsjlService = htpsjlService;
	}
	
	@RequestMapping(value="htpsjl/{htpsjlTableId}", method=RequestMethod.GET)
	@ResponseBody
	public DataWrapper<HTPSJLTable> getHTPSJLById(
		@PathVariable("htpsjlTableId") Long htpsjlTableId) {
		return htpsjlService.getHTPSJLTableById(htpsjlTableId);
	}
	
	@RequestMapping(value="htpsjl", method=RequestMethod.POST)
	@ResponseBody
	public DataWrapper<HTPSJLTable> addHTPSJL(
		@RequestParam("employeeId") Long employeeId,
		@RequestBody HTPSJLTable htpsjlTable) {
		return htpsjlService.addHTPSJLTable(htpsjlTable, employeeId);
	}
	
	//合同评审记录签字
	@RequestMapping(value="htpsjl/sign", method=RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> signHTPSJL(
		@RequestParam("taskId") Long taskId,
		@RequestParam("employeeId") Long employeeId) {
		return htpsjlService.signHTPSJLTable(taskId, employeeId);
	}
	
	@RequestMapping(value="htpsjl", method=RequestMethod.PUT)
	@ResponseBody
	public DataWrapper<HTPSJLTable> updateHTPSJL(
		@RequestBody HTPSJLTable htpsjlTable) {
		return htpsjlService.updateHTPSJLTable(htpsjlTable);
	}
	
	@RequestMapping(value="htpsjl/{htpsjlTableId}", method=RequestMethod.DELETE)
	@ResponseBody
	public DataWrapper<Void> deleteHTPSJL(
		@PathVariable("htpsjlTableId") Long htpsjlTableId) {
		return htpsjlService.deleteHTPSJLTable(htpsjlTableId);
	}
}
