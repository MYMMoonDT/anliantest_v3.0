package cn.edu.tongji.anliantest.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.tongji.anliantest.dao.AuthorityGroupDao;
import cn.edu.tongji.anliantest.model.AuthorityGroup;
import cn.edu.tongji.anliantest.service.AuthorityService;
import cn.edu.tongji.anliantest.util.DataWrapper;

@Service("AuthorityService")
public class AuthorityServiceImpl implements AuthorityService {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthorityServiceImpl.class);
	
	@Autowired
	private AuthorityGroupDao groupDao;
	
	@Override
	public DataWrapper<List<AuthorityGroup>> getAllAuthorityGroups() {
		return groupDao.getAllAuthorityGroups();
	}

}
