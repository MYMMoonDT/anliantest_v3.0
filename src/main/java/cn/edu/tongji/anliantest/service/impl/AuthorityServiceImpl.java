package cn.edu.tongji.anliantest.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.tongji.anliantest.dao.AuthorityGroupDao;
import cn.edu.tongji.anliantest.dao.AuthorityItemDao;
import cn.edu.tongji.anliantest.model.AuthorityGroup;
import cn.edu.tongji.anliantest.model.AuthorityItem;
import cn.edu.tongji.anliantest.service.AuthorityService;
import cn.edu.tongji.anliantest.util.DataWrapper;

@Service("AuthorityService")
public class AuthorityServiceImpl implements AuthorityService {

	private static final Logger logger = LoggerFactory
			.getLogger(AuthorityServiceImpl.class);

	@Autowired
	private AuthorityGroupDao authorityGroupDao;
	@Autowired
	private AuthorityItemDao authorityItemDao;

	@Override
	public DataWrapper<List<AuthorityGroup>> getAllAuthorityGroups() {
		return authorityGroupDao.getAllAuthorityGroups();
	}

	@Override
	public DataWrapper<List<AuthorityItem>> getAllAuthorityItems() {
		return authorityItemDao.getAllAuthorityItems();
	}

	@Override
	public DataWrapper<AuthorityGroup> updateAuthorityGroup(
			AuthorityGroup authorityGroup) {
		DataWrapper<AuthorityGroup> ret = new DataWrapper<AuthorityGroup>();

		authorityGroupDao.updateAuthorityGroup(authorityGroup);
		logger.info("更新权限组信息:" + authorityGroup.getName() + "("
				+ authorityGroup.getId() + ")");

		return ret;
	}

	@Override
	public DataWrapper<AuthorityGroup> addAuthorityGroupByName(
			String authorityGroupName) {
		DataWrapper<AuthorityGroup> ret = new DataWrapper<AuthorityGroup>();

		AuthorityGroup authorityGroup = new AuthorityGroup();
		authorityGroup.setName(authorityGroupName);
		authorityGroupDao.addAuthorityGroup(authorityGroup);

		ret.setData(authorityGroupDao.getAuthorityGroupById(authorityGroup
				.getId()));
		logger.info("添加权限组信息:" + authorityGroup.getName() + "("
				+ authorityGroup.getId() + ")");

		return ret;
	}

	@Override
	public DataWrapper<Void> deleteAuthorityGroup(Long authorityGroupId) {
		DataWrapper<Void> ret = new DataWrapper<Void>();

		authorityGroupDao.deleteAuthorityGroup(authorityGroupId);
		logger.info("删除权限组信息:" + "(" + authorityGroupId + ")");

		return ret;
	}
}
