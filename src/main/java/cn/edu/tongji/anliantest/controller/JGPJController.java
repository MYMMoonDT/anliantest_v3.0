package cn.edu.tongji.anliantest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.tongji.anliantest.service.JGPJService;

@Controller
@RequestMapping("api")
public class JGPJController {
	JGPJService jgpjService;
	
	@Autowired
	public JGPJController(JGPJService jgpjService) {
		this.jgpjService = jgpjService;
	}
}
