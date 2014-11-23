package cn.edu.tongji.anliantest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.tongji.anliantest.model.AuthorityGroup;
import cn.edu.tongji.anliantest.service.AuthorityService;
import cn.edu.tongji.anliantest.util.DataWrapper;

@Controller
@RequestMapping("api")
public class AuthorityController {
	AuthorityService authorityService;
	
	@Autowired
	public AuthorityController(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}
	
	@RequestMapping(value="authority/allGroups")
	@ResponseBody
	public DataWrapper<List<AuthorityGroup>> getAllAuthorityGroups() {
		return authorityService.getAllAuthorityGroups();
	}
}
