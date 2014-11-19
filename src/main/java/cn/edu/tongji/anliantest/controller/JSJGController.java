package cn.edu.tongji.anliantest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.tongji.anliantest.service.JSJGService;

@Controller
@RequestMapping("api")
public class JSJGController {
	JSJGService jsjgService;
	
	@Autowired
	public JSJGController(JSJGService jsjgService) {
		this.jsjgService = jsjgService;
	}
	
}
