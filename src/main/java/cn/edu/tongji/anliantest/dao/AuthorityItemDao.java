package cn.edu.tongji.anliantest.dao;

import java.util.List;

import cn.edu.tongji.anliantest.model.AuthorityItem;
import cn.edu.tongji.anliantest.model.Department;
import cn.edu.tongji.anliantest.util.DataWrapper;

public interface AuthorityItemDao {
public AuthorityItem getAuthorityItemById(Long authorityItemId);
	
	public AuthorityItem getAuthorityItemByDepartment(Department department);
	
	public Long addAuthorityItem(AuthorityItem authorityItem);
	
	public void updateAuthorityItem(AuthorityItem authorityItem);
	
	public void deleteAuthorityItem(Long authorityItemId);

	public DataWrapper<List<AuthorityItem>> getAllAuthorityItems();
}

