package cn.edu.tongji.anliantest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.tongji.anliantest.model.AuthorityGroup;
import cn.edu.tongji.anliantest.model.AuthorityItem;
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
	
	@RequestMapping(value="authority/group/all", method=RequestMethod.GET)
	@ResponseBody
	public DataWrapper<List<AuthorityGroup>> getAllAuthorityGroups() {
		return authorityService.getAllAuthorityGroups();
	}
	
	@RequestMapping(value="authority/item/all", method=RequestMethod.GET)
	@ResponseBody
	public DataWrapper<List<AuthorityItem>> getAllAuthorityItems() {
		return authorityService.getAllAuthorityItems();
	}
	
	@RequestMapping(value="authority/group/update", method=RequestMethod.POST)
	@ResponseBody
	public DataWrapper<AuthorityGroup> updateAuthorityGroup(@RequestBody AuthorityGroup group) {
		return authorityService.updateAuthorityGroup(group);
	}
	
	@RequestMapping(value="authority/group/add", method=RequestMethod.POST)
	@ResponseBody
	public DataWrapper<AuthorityGroup> addAuthorityGroup(@RequestBody String groupName) {
		return authorityService.addAuthorityGroupByName(groupName);
	}
	
	@RequestMapping(value="authority/group/delete/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public DataWrapper<AuthorityGroup> addAuthorityGroup(@PathVariable Long id) {
		return authorityService.deleteAuthorityGroup(id);
	}
}
