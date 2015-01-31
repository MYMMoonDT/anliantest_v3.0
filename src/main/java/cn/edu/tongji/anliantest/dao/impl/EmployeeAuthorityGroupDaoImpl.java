package cn.edu.tongji.anliantest.dao.impl;

import org.springframework.stereotype.Repository;

import cn.edu.tongji.anliantest.dao.AbstractHibernateDao;
import cn.edu.tongji.anliantest.dao.EmployeeAuthorityGroupDao;
import cn.edu.tongji.anliantest.model.EmployeeAuthorityGroup;

@Repository
public class EmployeeAuthorityGroupDaoImpl extends
		AbstractHibernateDao<EmployeeAuthorityGroup, Long> implements
		EmployeeAuthorityGroupDao {

	protected EmployeeAuthorityGroupDaoImpl() {
		super(EmployeeAuthorityGroup.class);
	}

	@Override
	public EmployeeAuthorityGroup getEmployeeAuthorityGroupById(
			Long employeeAuthorityGroupById) {
		return findById(employeeAuthorityGroupById);
	}

	@Override
	public Long addEmployeeAuthorityGroup(
			EmployeeAuthorityGroup employeeAuthorityGroup) {
		return add(employeeAuthorityGroup);
	}

	@Override
	public void updateEmployeeAuthorityGroup(
			EmployeeAuthorityGroup employeeAuthorityGroup) {
		saveOrUpdate(employeeAuthorityGroup);
	}

	@Override
	public void deleteEmployeeAuthorityGroup(Long employeeAuthorityGroupById) {
		delete(findById(employeeAuthorityGroupById));
	}

}
