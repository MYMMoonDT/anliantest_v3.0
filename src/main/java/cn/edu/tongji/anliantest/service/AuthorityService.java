package cn.edu.tongji.anliantest.service;

import java.util.List;

import cn.edu.tongji.anliantest.model.AuthorityGroup;
import cn.edu.tongji.anliantest.model.AuthorityItem;
import cn.edu.tongji.anliantest.util.DataWrapper;

public interface AuthorityService {
	
	public DataWrapper<List<AuthorityGroup>> getAllAuthorityGroups();

	public DataWrapper<List<AuthorityItem>> getAllAuthorityItems();

	public DataWrapper<AuthorityGroup> updateAuthorityGroup(AuthorityGroup group);

	public DataWrapper<AuthorityGroup> addAuthorityGroupByName(String groupName);

	public DataWrapper<AuthorityGroup> deleteAuthorityGroup(Long id);
}
