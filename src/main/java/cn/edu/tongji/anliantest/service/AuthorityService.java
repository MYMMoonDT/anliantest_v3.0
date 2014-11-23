package cn.edu.tongji.anliantest.service;

import java.util.List;

import cn.edu.tongji.anliantest.model.AuthorityGroup;
import cn.edu.tongji.anliantest.util.DataWrapper;

public interface AuthorityService {
	
	public DataWrapper<List<AuthorityGroup>> getAllAuthorityGroups();	
}
