package cn.edu.tongji.anliantest.dao;

import java.util.List;

import cn.edu.tongji.anliantest.model.AuthorityGroup;
import cn.edu.tongji.anliantest.model.Department;
import cn.edu.tongji.anliantest.util.DataWrapper;

public interface AuthorityGroupDao {
public AuthorityGroup getAuthorityGroupById(Long authorityGroupId);
	
	public AuthorityGroup getAuthorityGroupByDepartment(Department department);
	
	public Long addAuthorityGroup(AuthorityGroup authorityGroup);
	
	public AuthorityGroup updateAuthorityGroup(AuthorityGroup authorityGroup);
	
	public void deleteAuthorityGroup(Long authorityGroupId);

	public DataWrapper<List<AuthorityGroup>> getAllAuthorityGroups();
}

