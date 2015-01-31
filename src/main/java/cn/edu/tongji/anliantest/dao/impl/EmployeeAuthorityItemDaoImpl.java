package cn.edu.tongji.anliantest.dao.impl;

import org.springframework.stereotype.Repository;

import cn.edu.tongji.anliantest.dao.AbstractHibernateDao;
import cn.edu.tongji.anliantest.dao.EmployeeAuthorityItemDao;
import cn.edu.tongji.anliantest.model.EmployeeAuthorityItem;

@Repository
public class EmployeeAuthorityItemDaoImpl extends AbstractHibernateDao<EmployeeAuthorityItem, Long> implements EmployeeAuthorityItemDao{

	protected EmployeeAuthorityItemDaoImpl() {
		super(EmployeeAuthorityItem.class);
	}

	@Override
	public EmployeeAuthorityItem getEmployeeAuthorityItemById(
			Long employeeAuthorityItemById) {
		return findById(employeeAuthorityItemById);
	}

	@Override
	public Long addEmployeeAuthorityItem(
			EmployeeAuthorityItem employeeAuthorityItem) {
		return add(employeeAuthorityItem);
	}

	@Override
	public void updateEmployeeAuthorityItem(
			EmployeeAuthorityItem employeeAuthorityItem) {
		saveOrUpdate(employeeAuthorityItem);
	}

	@Override
	public void deleteEmployeeAuthorityItem(Long employeeAuthorityItemById) {
		delete(findById(employeeAuthorityItemById));
	}
	
}
