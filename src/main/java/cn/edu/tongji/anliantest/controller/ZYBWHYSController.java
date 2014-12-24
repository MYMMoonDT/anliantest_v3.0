package cn.edu.tongji.anliantest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.tongji.anliantest.model.experiment.ZYBWHYSItem;
import cn.edu.tongji.anliantest.service.ZYBWHYSService;
import cn.edu.tongji.anliantest.util.DataWrapper;

@Controller
@RequestMapping("api")
public class ZYBWHYSController {
	ZYBWHYSService zybwhysService;
	
	@Autowired
	public ZYBWHYSController(ZYBWHYSService zybwhysService){
		this.zybwhysService = zybwhysService;
	}
	
	@RequestMapping(value="zybwhys/all", method=RequestMethod.GET)
	@ResponseBody
	public DataWrapper<List<ZYBWHYSItem>> getAllZYBWHYSItem() {
		return zybwhysService.getAllZYBWHYSItem();
	}
}
